package com.youlai.boot.building.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.building.model.entity.BuildingPhotos;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.building.model.query.BuildingPhotosQuery;
import com.youlai.boot.building.model.vo.BuildingPhotosVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 建筑物照片Mapper接口
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Mapper
public interface BuildingPhotosMapper extends BaseMapper<BuildingPhotos> {

    /**
     * 获取建筑物照片分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return
     */
    Page<BuildingPhotosVO> getBuildingPhotosPage(Page<BuildingPhotosVO> page, BuildingPhotosQuery queryParams);

}
