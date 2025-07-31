package com.youlai.boot.building.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 古建筑列表视图对象
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Getter
@Setter
@Schema( description = "古建筑列表视图对象")
public class BuildingListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "建筑物名称")
    private String buildingName;
    @Schema(description = "详细描述")
    private String buildingDescription;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
