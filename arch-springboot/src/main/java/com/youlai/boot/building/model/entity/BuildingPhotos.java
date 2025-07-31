package com.youlai.boot.building.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;

/**
 * 建筑物照片实体对象
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Getter
@Setter
@TableName("building_photos")
public class BuildingPhotos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 建筑名称ID
     */
    private String buildingId;
    /**
     * 建筑部位
     */
    private String buildingPart;
    /**
     * 照片URL地址
     */
    private String photoUrl;
    /**
     * 照片描述
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;
}
