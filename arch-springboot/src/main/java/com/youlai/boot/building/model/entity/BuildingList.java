package com.youlai.boot.building.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;

/**
 * 古建筑列表实体对象
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Getter
@Setter
@TableName("building_list")
public class BuildingList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 建筑物名称
     */
    private String buildingName;
    /**
     * 详细描述
     */
    private String buildingDescription;
    /**
     * 状态：1-正常，0-删除
     */
    private Integer status;
}
