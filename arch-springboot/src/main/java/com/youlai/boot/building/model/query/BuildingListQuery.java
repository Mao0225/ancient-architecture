package com.youlai.boot.building.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 古建筑列表分页查询对象
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Schema(description ="古建筑列表查询对象")
@Getter
@Setter
public class BuildingListQuery extends BasePageQuery {
    @Schema(description = "建筑物名称")
    private String buildingName;


}
