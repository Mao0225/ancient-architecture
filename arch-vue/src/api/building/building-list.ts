import request from "@/utils/request";

const BUILDINGLIST_BASE_URL = "/api/v1/buildingLists";

const BuildingListAPI = {
  /** 获取古建筑列表分页数据 */
  getPage(queryParams?: BuildingListPageQuery) {
    return request<any, PageResult<BuildingListPageVO[]>>({
      url: `${BUILDINGLIST_BASE_URL}/page`,
      method: "get",
      params: queryParams,
    });
  },
  /**
   * 获取古建筑列表表单数据
   *
   * @param id 古建筑列表ID
   * @returns 古建筑列表表单数据
   */
  getFormData(id: number) {
    return request<any, BuildingListForm>({
      url: `${BUILDINGLIST_BASE_URL}/${id}/form`,
      method: "get",
    });
  },

  /** 添加古建筑列表*/
  add(data: BuildingListForm) {
    return request({
      url: `${BUILDINGLIST_BASE_URL}`,
      method: "post",
      data: data,
    });
  },

  /** 获取古建筑下拉数据源 */
  getOptions() {
    return request<any, OptionType[]>({
      url: `${BUILDINGLIST_BASE_URL}/options`,
      method: "get",
    });
  },
  /**
   * 更新古建筑列表
   *
   * @param id 古建筑列表ID
   * @param data 古建筑列表表单数据
   */
  update(id: number, data: BuildingListForm) {
    return request({
      url: `${BUILDINGLIST_BASE_URL}/${id}`,
      method: "put",
      data: data,
    });
  },

  /**
   * 批量删除古建筑列表，多个以英文逗号(,)分割
   *
   * @param ids 古建筑列表ID字符串，多个以英文逗号(,)分割
   */
  deleteByIds(ids: string) {
    return request({
      url: `${BUILDINGLIST_BASE_URL}/${ids}`,
      method: "delete",
    });
  },
};

export default BuildingListAPI;

/** 古建筑列表分页查询参数 */
export interface BuildingListPageQuery extends PageQuery {
  buildingName?: string;
}

/** 古建筑列表表单对象 */
export interface BuildingListForm {
  /** 主键ID */
  id?: number;
  /** 建筑物名称 */
  buildingName?: string;
  /** 详细描述 */
  buildingDescription?: string;
  /** 状态：1-正常，0-删除 */
  status?: number;
}

/** 古建筑列表分页对象 */
export interface BuildingListPageVO {
  /** 主键ID */
  id: number;
  /** 建筑物名称 */
  buildingName: string;
  /** 详细描述 */
  buildingDescription?: string;
  /** 创建时间 */
  createTime?: Date;
}
