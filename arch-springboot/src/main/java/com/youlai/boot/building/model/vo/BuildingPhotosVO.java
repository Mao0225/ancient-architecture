package com.youlai.boot.building.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 建筑物照片视图对象
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Getter
@Setter
@Schema( description = "建筑物照片视图对象")
public class BuildingPhotosVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "建筑名称ID")
    private String buildingId;
    @Schema(description = "建筑部位")
    private String buildingPart;
    @Schema(description = "照片URL地址")
    private String photoUrl;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "照片描述")
    private String description;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "建筑名称")
    private String buildingName;
}
