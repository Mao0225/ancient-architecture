import request from "@/utils/request";

const DEPT_BASE_URL = "/api/v1/dept";

const DeptAPI = {
  /**
   * 获取家庭组列表
   *
   * @param queryParams 查询参数（可选）
   * @returns 家庭组树形表格数据
   */
  getList(queryParams?: DeptQuery) {
    return request<any, DeptVO[]>({
      url: `${DEPT_BASE_URL}`,
      method: "get",
      params: queryParams,
    });
  },

  /** 获取家庭组下拉列表 */
  getOptions() {
    return request<any, OptionType[]>({
      url: `${DEPT_BASE_URL}/options`,
      method: "get",
    });
  },

  /**
   * 获取家庭组表单数据
   *
   * @param id 家庭组ID
   * @returns 家庭组表单数据
   */
  getFormData(id: number) {
    return request<any, DeptForm>({
      url: `${DEPT_BASE_URL}/${id}/form`,
      method: "get",
    });
  },

  /**
   * 新增家庭组
   *
   * @param data 家庭组表单数据
   * @returns 请求结果
   */
  create(data: DeptForm) {
    return request({
      url: `${DEPT_BASE_URL}`,
      method: "post",
      data: data,
    });
  },

  /**
   * 修改家庭组
   *
   * @param id 家庭组ID
   * @param data 家庭组表单数据
   * @returns 请求结果
   */
  update(id: string, data: DeptForm) {
    return request({
      url: `${DEPT_BASE_URL}/${id}`,
      method: "put",
      data: data,
    });
  },

  /**
   * 删除家庭组
   *
   * @param ids 家庭组ID，多个以英文逗号(,)分隔
   * @returns 请求结果
   */
  deleteByIds(ids: string) {
    return request({
      url: `${DEPT_BASE_URL}/${ids}`,
      method: "delete",
    });
  },
};

export default DeptAPI;

/** 家庭组查询参数 */
export interface DeptQuery {
  /** 搜索关键字 */
  keywords?: string;
  /** 状态 */
  status?: number;
}

/** 家庭组类型 */
export interface DeptVO {
  /** 子家庭组 */
  children?: DeptVO[];
  /** 创建时间 */
  createTime?: Date;
  /** 家庭组ID */
  id?: number;
  /** 家庭组名称 */
  name?: string;
  /** 家庭组编号 */
  code?: string;
  /** 父家庭组ID */
  parentId?: number;
  /** 排序 */
  sort?: number;
  /** 状态(1:启用；0:禁用) */
  status?: number;
  /** 修改时间 */
  updateTime?: Date;
}

/** 家庭组表单类型 */
export interface DeptForm {
  /** 家庭组ID(新增不填) */
  id?: string;
  /** 家庭组名称 */
  name?: string;
  /** 家庭组编号 */
  code?: string;
  /** 父家庭组ID */
  parentId: string;
  /** 排序 */
  sort?: number;
  /** 状态(1:启用；0：禁用) */
  status?: number;
}
