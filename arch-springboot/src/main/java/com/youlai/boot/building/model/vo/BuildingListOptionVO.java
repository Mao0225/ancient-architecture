package com.youlai.boot.building.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema( description = "古建筑列表选项对象")
public class BuildingListOptionVO {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "建筑物名称")
    private String buildingName;
}
