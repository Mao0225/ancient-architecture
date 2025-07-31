import request from "@/utils/request";

const BUILDINGPHOTOS_BASE_URL = "/api/v1/buildingPhotoss";

const BuildingPhotosAPI = {
  /** 获取建筑物照片分页数据 */
  getPage(queryParams?: BuildingPhotosPageQuery) {
    return request<any, PageResult<BuildingPhotosPageVO[]>>({
      url: `${BUILDINGPHOTOS_BASE_URL}/page`,
      method: "get",
      params: queryParams,
    });
  },
  /**
   * 获取建筑物照片表单数据
   *
   * @param id 建筑物照片ID
   * @returns 建筑物照片表单数据
   */
  getFormData(id: number) {
    return request<any, BuildingPhotosForm>({
      url: `${BUILDINGPHOTOS_BASE_URL}/${id}/form`,
      method: "get",
    });
  },

  /** 添加建筑物照片*/
  add(data: BuildingPhotosForm) {
    return request({
      url: `${BUILDINGPHOTOS_BASE_URL}`,
      method: "post",
      data: data,
    });
  },

  /**
   * 更新建筑物照片
   *
   * @param id 建筑物照片ID
   * @param data 建筑物照片表单数据
   */
  update(id: number, data: BuildingPhotosForm) {
    return request({
      url: `${BUILDINGPHOTOS_BASE_URL}/${id}`,
      method: "put",
      data: data,
    });
  },

  /**
   * 批量删除建筑物照片，多个以英文逗号(,)分割
   *
   * @param ids 建筑物照片ID字符串，多个以英文逗号(,)分割
   */
  deleteByIds(ids: string) {
    return request({
      url: `${BUILDINGPHOTOS_BASE_URL}/${ids}`,
      method: "delete",
    });
  },
};

export default BuildingPhotosAPI;

/** 建筑物照片分页查询参数 */
export interface BuildingPhotosPageQuery extends PageQuery {
  buildingId?: number;
}

/** 建筑物照片表单对象 */
export interface BuildingPhotosForm {
  /** 主键ID */
  id?: number;
  /** 建筑名称ID */
  buildingId?: number;
  /** 建筑部位 */
  buildingPart?: string;
  /** 照片URL地址 */
  photoUrl?: string;
  /** 照片描述 */
  description?: string;
  /** 状态 */
  status?: number;
}

/** 建筑物照片分页对象 */
export interface BuildingPhotosPageVO {
  /** 主键ID */
  id?: number;
  /** 建筑名称ID */
  buildingId?: string;
  /** 建筑部位 */
  buildingPart?: string;
  /** 照片URL地址 */
  photoUrl?: string;
  /** 创建时间 */
  createTime?: Date;
  /** 照片描述 */
  description?: string;
  /** 状态 */
  status?: number;

  /** 所属建筑物名称 */
  buildingName?: string;

  isVisible?: boolean; // 添加 isVisible 属性
}
