package com.youlai.boot.building.service;

import com.youlai.boot.building.model.entity.BuildingPhotos;
import com.youlai.boot.building.model.form.BuildingPhotosForm;
import com.youlai.boot.building.model.query.BuildingPhotosQuery;
import com.youlai.boot.building.model.vo.BuildingPhotosVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 建筑物照片服务类
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
public interface BuildingPhotosService extends IService<BuildingPhotos> {

    /**
     *建筑物照片分页列表
     *
     * @return
     */
    IPage<BuildingPhotosVO> getBuildingPhotosPage(BuildingPhotosQuery queryParams);

    /**
     * 获取建筑物照片表单数据
     *
     * @param id 建筑物照片ID
     * @return
     */
     BuildingPhotosForm getBuildingPhotosFormData(Long id);

    /**
     * 新增建筑物照片
     *
     * @param formData 建筑物照片表单对象
     * @return
     */
    boolean saveBuildingPhotos(BuildingPhotosForm formData);

    /**
     * 修改建筑物照片
     *
     * @param id   建筑物照片ID
     * @param formData 建筑物照片表单对象
     * @return
     */
    boolean updateBuildingPhotos(Long id, BuildingPhotosForm formData);

    /**
     * 删除建筑物照片
     *
     * @param ids 建筑物照片ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteBuildingPhotoss(String ids);

}
