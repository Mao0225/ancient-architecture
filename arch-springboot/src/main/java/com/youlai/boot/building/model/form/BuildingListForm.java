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
 * 古建筑列表表单对象
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Getter
@Setter
@Schema(description = "古建筑列表表单对象")
public class BuildingListForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "建筑物名称")
    @NotBlank(message = "建筑物名称不能为空")
    @Size(max=200, message="建筑物名称长度不能超过200个字符")
    private String buildingName;

    @Schema(description = "详细描述")
    @Size(max=65535, message="详细描述长度不能超过65535个字符")
    private String buildingDescription;

    @Schema(description = "状态：1-正常，0-删除")
    private Integer status;

}
