package com.youlai.boot.building.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 建筑物照片分页查询对象
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Schema(description ="建筑物照片查询对象")
@Getter
@Setter
public class BuildingPhotosQuery extends BasePageQuery {
    @Schema(description = "ID")
    private Long buildingId;
}
