package com.youlai.boot.building.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.building.mapper.BuildingPhotosMapper;
import com.youlai.boot.building.service.BuildingPhotosService;
import com.youlai.boot.building.model.entity.BuildingPhotos;
import com.youlai.boot.building.model.form.BuildingPhotosForm;
import com.youlai.boot.building.model.query.BuildingPhotosQuery;
import com.youlai.boot.building.model.vo.BuildingPhotosVO;
import com.youlai.boot.building.converter.BuildingPhotosConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 建筑物照片服务实现类
 *
 * @author youlaitech
 * @since 2025-06-07 13:33
 */
@Service
@RequiredArgsConstructor
public class BuildingPhotosServiceImpl extends ServiceImpl<BuildingPhotosMapper, BuildingPhotos> implements BuildingPhotosService {

    private final BuildingPhotosConverter buildingPhotosConverter;

    /**
    * 获取建筑物照片分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<BuildingPhotosVO>} 建筑物照片分页列表
    */
    @Override
    public IPage<BuildingPhotosVO> getBuildingPhotosPage(BuildingPhotosQuery queryParams) {
        Page<BuildingPhotosVO> pageVO = this.baseMapper.getBuildingPhotosPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取建筑物照片表单数据
     *
     * @param id 建筑物照片ID
     * @return
     */
    @Override
    public BuildingPhotosForm getBuildingPhotosFormData(Long id) {
        BuildingPhotos entity = this.getById(id);
        return buildingPhotosConverter.toForm(entity);
    }
    
    /**
     * 新增建筑物照片
     *
     * @param formData 建筑物照片表单对象
     * @return
     */
    @Override
    public boolean saveBuildingPhotos(BuildingPhotosForm formData) {
        BuildingPhotos entity = buildingPhotosConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新建筑物照片
     *
     * @param id   建筑物照片ID
     * @param formData 建筑物照片表单对象
     * @return
     */
    @Override
    public boolean updateBuildingPhotos(Long id,BuildingPhotosForm formData) {
        BuildingPhotos entity = buildingPhotosConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除建筑物照片
     *
     * @param ids 建筑物照片ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteBuildingPhotoss(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的建筑物照片数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
