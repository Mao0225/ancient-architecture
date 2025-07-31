package com.youlai.boot.building.controller;

import com.youlai.boot.building.service.BuildingPhotosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youlai.boot.building.model.form.BuildingPhotosForm;
import com.youlai.boot.building.model.query.BuildingPhotosQuery;
import com.youlai.boot.building.model.vo.BuildingPhotosVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * 建筑物照片前端控制层
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Tag(name = "建筑物照片接口")
@RestController
@RequestMapping("/api/v1/buildingPhotoss")
@RequiredArgsConstructor
public class BuildingPhotosController  {

    private final BuildingPhotosService buildingPhotosService;

    @Operation(summary = "建筑物照片分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('building:buildingPhotos:query')")
    public PageResult<BuildingPhotosVO> getBuildingPhotosPage(BuildingPhotosQuery queryParams ) {
        IPage<BuildingPhotosVO> result = buildingPhotosService.getBuildingPhotosPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增建筑物照片")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('building:buildingPhotos:add')")
    public Result<Void> saveBuildingPhotos(@RequestBody @Valid BuildingPhotosForm formData ) {
        boolean result = buildingPhotosService.saveBuildingPhotos(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取建筑物照片表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('building:buildingPhotos:edit')")
    public Result<BuildingPhotosForm> getBuildingPhotosForm(
        @Parameter(description = "建筑物照片ID") @PathVariable Long id
    ) {
        BuildingPhotosForm formData = buildingPhotosService.getBuildingPhotosFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改建筑物照片")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('building:buildingPhotos:edit')")
    public Result<Void> updateBuildingPhotos(
            @Parameter(description = "建筑物照片ID") @PathVariable Long id,
            @RequestBody @Validated BuildingPhotosForm formData
    ) {
        boolean result = buildingPhotosService.updateBuildingPhotos(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除建筑物照片")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('building:buildingPhotos:delete')")
    public Result<Void> deleteBuildingPhotoss(
        @Parameter(description = "建筑物照片ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = buildingPhotosService.deleteBuildingPhotoss(ids);
        return Result.judge(result);
    }
}
