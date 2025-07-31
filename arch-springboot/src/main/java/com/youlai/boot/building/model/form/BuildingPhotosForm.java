package com.youlai.boot.building.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

/**
 * 建筑物照片表单对象
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Getter
@Setter
@Schema(description = "建筑物照片表单对象")
public class BuildingPhotosForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "建筑名称ID")
    @NotBlank(message = "建筑名称ID不能为空")
    @Size(max=200, message="建筑名称ID长度不能超过200个字符")
    private String buildingId;

    @Schema(description = "建筑部位")
    @Size(max=100, message="建筑部位长度不能超过100个字符")
    private String buildingPart;

    @Schema(description = "照片URL地址")
    @Size(max=500, message="照片URL地址长度不能超过500个字符")
    private String photoUrl;

    @Schema(description = "照片描述")
    @Size(max=65535, message="照片描述长度不能超过65535个字符")
    private String description;

    @Schema(description = "状态")
    private Integer status;


}
