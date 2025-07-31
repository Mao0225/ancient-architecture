package com.youlai.boot.shared.file.controller;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import jakarta.servlet.http.HttpServletRequest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 上传文件访问控制器
 * 专门处理 /uploads/ 和 /project-uploads/ 路径的异步文件访问
 */
@RestController
public class UploadsController {

    @Value("${oss.local.storage-path}")
    private String storagePath;

    // 自定义线程池
    private static final ExecutorService FILE_IO_EXECUTOR = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2
    );

    // 预定义内容类型映射
    private static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<>();
    static {
        CONTENT_TYPE_MAP.put("jpg", "image/jpeg");
        CONTENT_TYPE_MAP.put("jpeg", "image/jpeg");
        CONTENT_TYPE_MAP.put("png", "image/png");
        CONTENT_TYPE_MAP.put("gif", "image/gif");
        CONTENT_TYPE_MAP.put("webp", "image/webp");
    }

    /**
     * 异步获取uploads目录下的文件
     */
    @GetMapping("/uploads/**")
    public DeferredResult<ResponseEntity<Resource>> getUploadsFile(HttpServletRequest request) {
        return processFileRequest(request, "/uploads/", storagePath);
    }

    /**
     * 异步获取project-uploads目录下的文件
     */
    @GetMapping("/project-uploads/**")
    public DeferredResult<ResponseEntity<Resource>> getProjectUploadsFile(HttpServletRequest request) {
        String projectUploads = System.getProperty("user.dir") + "/uploads/";
        return processFileRequest(request, "/project-uploads/", projectUploads);
    }

    /**
     * 通用的文件请求处理方法
     */
    private DeferredResult<ResponseEntity<Resource>> processFileRequest(
            HttpServletRequest request, String urlPrefix, String basePath) {

        DeferredResult<ResponseEntity<Resource>> deferredResult = new DeferredResult<>(30000L);

        try {
            String requestURI = request.getRequestURI();
            String filename = requestURI.substring(urlPrefix.length());

            // 使用自定义线程池
            CompletableFuture.supplyAsync(() -> getFileResponse(basePath, filename, request), FILE_IO_EXECUTOR)
                    .whenComplete((result, throwable) -> {
                        if (throwable != null) {
                            deferredResult.setErrorResult(ResponseEntity.status(500).body(null));
                        } else {
                            deferredResult.setResult(result);
                        }
                    });

        } catch (Exception e) {
            deferredResult.setErrorResult(ResponseEntity.status(500).body(null));
        }

        return deferredResult;
    }

    /**
     * 通用的文件响应方法，支持压缩和缓存
     */
    private ResponseEntity<Resource> getFileResponse(String basePath, String filename, HttpServletRequest request) {
        try {
            Path filePath = Paths.get(basePath, filename);
            File file = filePath.toFile();

            if (!file.exists() || !file.isFile()) {
                return ResponseEntity.notFound().build();
            }

            // 检查缓存头
            String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);
            String eTag = generateETag(file);
            if (ifNoneMatch != null && ifNoneMatch.equals(eTag)) {
                return ResponseEntity.status(304).build(); // 未修改，直接返回 304
            }

            // 确定内容类型
            String contentType = determineContentType(filename);
            boolean isImage = contentType.startsWith("image/");

            Resource resource;
            if (isImage) {
                // 动态压缩图片
                resource = compressImage(file, contentType);
            } else {
                resource = new FileSystemResource(file);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate")
                    .header(HttpHeaders.ETAG, eTag)
                    .header(HttpHeaders.LAST_MODIFIED, String.valueOf(file.lastModified()))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 动态压缩图片
     */
    private Resource compressImage(File file, String contentType) throws IOException {
        // 只对 JPEG 和 PNG 进行压缩
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            return new FileSystemResource(file);
        }

        // 读取原始图片
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return new FileSystemResource(file);
        }

        // 创建临时文件存储压缩后的图片
        String tempFileName = file.getName() + "_compressed_" + System.currentTimeMillis();
        File compressedFile = new File(System.getProperty("java.io.tmpdir"), tempFileName);

        // 设置压缩质量（0.7f 为中等质量，可调整）
        float quality = 0.7f;
        try (FileOutputStream fos = new FileOutputStream(compressedFile)) {
            ImageIO.write(image, contentType.equals("image/jpeg") ? "jpg" : "png", fos);
        }

        return new FileSystemResource(compressedFile);
    }

    /**
     * 生成 ETag
     */
    private String generateETag(File file) {
        return "\"" + file.getName() + "_" + file.lastModified() + "_" + file.length() + "\"";
    }

    /**
     * 根据文件名确定内容类型
     */
    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        return CONTENT_TYPE_MAP.getOrDefault(extension, "application/octet-stream");
    }

    /**
     * 清理线程池（在应用关闭时调用）
     */
    @PreDestroy
    private void shutdownExecutor() {
        FILE_IO_EXECUTOR.shutdown();
    }
}