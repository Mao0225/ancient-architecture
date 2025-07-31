package com.youlai.boot.building.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.building.model.entity.BuildingPhotos;
import com.youlai.boot.building.model.form.BuildingPhotosForm;

/**
 * 建筑物照片对象转换器
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Mapper(componentModel = "spring")
public interface BuildingPhotosConverter{

    BuildingPhotosForm toForm(BuildingPhotos entity);

    BuildingPhotos toEntity(BuildingPhotosForm formData);
}