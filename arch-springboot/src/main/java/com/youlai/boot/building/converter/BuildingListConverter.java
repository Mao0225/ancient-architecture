package com.youlai.boot.building.converter;

import com.youlai.boot.building.model.vo.BuildingListOptionVO;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.detect.model.vo.RaspiOptionVO;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.building.model.entity.BuildingList;
import com.youlai.boot.building.model.form.BuildingListForm;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 古建筑列表对象转换器
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Mapper(componentModel = "spring")
public interface BuildingListConverter{

    BuildingListForm toForm(BuildingList entity);

    BuildingList toEntity(BuildingListForm formData);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "buildingName")
    })
    Option<Long> toOption(BuildingListOptionVO building);

    List<Option<Long>> toOptions(List<BuildingListOptionVO> building);
}