package com.youlai.boot.building.service.impl;

import com.youlai.boot.building.model.vo.BuildingListOptionVO;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.detect.model.vo.RaspiOptionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.building.mapper.BuildingListMapper;
import com.youlai.boot.building.service.BuildingListService;
import com.youlai.boot.building.model.entity.BuildingList;
import com.youlai.boot.building.model.form.BuildingListForm;
import com.youlai.boot.building.model.query.BuildingListQuery;
import com.youlai.boot.building.model.vo.BuildingListVO;
import com.youlai.boot.building.converter.BuildingListConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 古建筑列表服务实现类
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Service
@RequiredArgsConstructor
public class BuildingListServiceImpl extends ServiceImpl<BuildingListMapper, BuildingList> implements BuildingListService {

    private final BuildingListConverter buildingListConverter;

    /**
    * 获取古建筑列表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<BuildingListVO>} 古建筑列表分页列表
    */
    @Override
    public IPage<BuildingListVO> getBuildingListPage(BuildingListQuery queryParams) {
        Page<BuildingListVO> pageVO = this.baseMapper.getBuildingListPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取古建筑列表表单数据
     *
     * @param id 古建筑列表ID
     * @return
     */
    @Override
    public BuildingListForm getBuildingListFormData(Long id) {
        BuildingList entity = this.getById(id);
        System.out.println("获取古建筑信息----0"+entity);
        return buildingListConverter.toForm(entity);
    }
    
    /**
     * 新增古建筑列表
     *
     * @param formData 古建筑列表表单对象
     * @return
     */
    @Override
    public boolean saveBuildingList(BuildingListForm formData) {
        BuildingList entity = buildingListConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新古建筑列表
     *
     * @param id   古建筑列表ID
     * @param formData 古建筑列表表单对象
     * @return
     */
    @Override
    public boolean updateBuildingList(Long id,BuildingListForm formData) {
        BuildingList entity = buildingListConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除古建筑列表
     *
     * @param ids 古建筑列表ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteBuildingLists(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的古建筑列表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public List<Option<Long>> listBuildingOptions() {
        List<BuildingListOptionVO> List = this.baseMapper.listBuildingOptions();
        // 实体转换
        return buildingListConverter.toOptions(List);
    }

}
