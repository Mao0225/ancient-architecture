package com.youlai.boot.building.controller;

import com.youlai.boot.building.service.BuildingListService;
import com.youlai.boot.common.model.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youlai.boot.building.model.form.BuildingListForm;
import com.youlai.boot.building.model.query.BuildingListQuery;
import com.youlai.boot.building.model.vo.BuildingListVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 古建筑列表前端控制层
 *
 * @author Mao
 * @since 2025-06-07 13:05
 */
@Tag(name = "古建筑列表接口")
@RestController
@RequestMapping("/api/v1/buildingLists")
@RequiredArgsConstructor
public class BuildingListController  {

    private final BuildingListService buildingListService;

    @Operation(summary = "古建筑列表分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('building:buildingList:query')")
    public PageResult<BuildingListVO> getBuildingListPage(BuildingListQuery queryParams ) {
        IPage<BuildingListVO> result = buildingListService.getBuildingListPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增古建筑列表")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('building:buildingList:add')")
    public Result<Void> saveBuildingList(@RequestBody @Valid BuildingListForm formData ) {
        boolean result = buildingListService.saveBuildingList(formData);
        return Result.judge(result);
    }

    @Operation(summary = "古建筑下拉列表")
    @GetMapping("/options")
    public Result<List<Option<Long>>> listBuildingOptions() {
        List<Option<Long>> list = buildingListService.listBuildingOptions();
        return Result.success(list);
    }

    @Operation(summary = "获取古建筑列表表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('building:buildingList:edit')")
    public Result<BuildingListForm> getBuildingListForm(
        @Parameter(description = "古建筑列表ID") @PathVariable Long id
    ) {
        BuildingListForm formData = buildingListService.getBuildingListFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改古建筑列表")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('building:buildingList:edit')")
    public Result<Void> updateBuildingList(
            @Parameter(description = "古建筑列表ID") @PathVariable Long id,
            @RequestBody @Validated BuildingListForm formData
    ) {
        boolean result = buildingListService.updateBuildingList(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除古建筑列表")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('building:buildingList:delete')")
    public Result<Void> deleteBuildingLists(
        @Parameter(description = "古建筑列表ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = buildingListService.deleteBuildingLists(ids);
        return Result.judge(result);
    }
}
