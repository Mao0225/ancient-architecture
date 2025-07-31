package com.youlai.boot.building.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.building.model.entity.BuildingList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.building.model.query.BuildingListQuery;
import com.youlai.boot.building.model.vo.BuildingListOptionVO;
import com.youlai.boot.building.model.vo.BuildingListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 古建筑列表Mapper接口
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Mapper
public interface BuildingListMapper extends BaseMapper<BuildingList> {

    /**
     * 获取古建筑列表分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return
     */
    Page<BuildingListVO> getBuildingListPage(Page<BuildingListVO> page, BuildingListQuery queryParams);

    List<BuildingListOptionVO> listBuildingOptions();
}
