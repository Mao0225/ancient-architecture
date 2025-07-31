package com.youlai.boot.building.service;

import com.youlai.boot.building.model.entity.BuildingList;
import com.youlai.boot.building.model.form.BuildingListForm;
import com.youlai.boot.building.model.query.BuildingListQuery;
import com.youlai.boot.building.model.vo.BuildingListVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;

import java.util.List;

/**
 * 古建筑列表服务类
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
public interface BuildingListService extends IService<BuildingList> {

    /**
     *古建筑列表分页列表
     *
     * @return
     */
    IPage<BuildingListVO> getBuildingListPage(BuildingListQuery queryParams);

    /**
     * 获取古建筑列表表单数据
     *
     * @param id 古建筑列表ID
     * @return
     */
     BuildingListForm getBuildingListFormData(Long id);

    /**
     * 新增古建筑列表
     *
     * @param formData 古建筑列表表单对象
     * @return
     */
    boolean saveBuildingList(BuildingListForm formData);

    /**
     * 修改古建筑列表
     *
     * @param id   古建筑列表ID
     * @param formData 古建筑列表表单对象
     * @return
     */
    boolean updateBuildingList(Long id, BuildingListForm formData);

    /**
     * 删除古建筑列表
     *
     * @param ids 古建筑列表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteBuildingLists(String ids);

    List<Option<Long>> listBuildingOptions();
}
