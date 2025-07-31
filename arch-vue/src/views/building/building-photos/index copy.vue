<template>
  <div class="app-container">
    <div class="search-bar">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item>
          <el-button type="primary" @click="handleQuery">
            <template #icon><Search /></template>
            搜索
          </el-button>
          <el-button @click="handleResetQuery">
            <template #icon><Refresh /></template>
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card shadow="never">
      <div class="mb-10px">
        <el-button
          v-hasPerm="['building:buildingPhotos:add']"
          type="success"
          @click="handleOpenDialog()"
        >
          <template #icon><Plus /></template>
          新增
        </el-button>
        <el-button
          v-hasPerm="['building:buildingPhotos:delete']"
          type="danger"
          :disabled="removeIds.length === 0"
          @click="handleDelete()"
        >
          <template #icon><Delete /></template>
          删除
        </el-button>
      </div>

      <el-table
        ref="dataTableRef"
        v-loading="loading"
        :data="pageData"
        highlight-current-row
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column key="id" label="主键ID" prop="id" min-width="150" align="center" />
        <el-table-column
          key="buildingName"
          label="建筑名称"
          prop="buildingName"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="buildingPart"
          label="建筑部位"
          prop="buildingPart"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="photoUrl"
          label="照片URL地址"
          prop="photoUrl"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="createTime"
          label="创建时间"
          prop="createTime"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="description"
          label="照片描述"
          prop="description"
          min-width="150"
          align="center"
        />
        <el-table-column key="status" label="状态" prop="status" min-width="150" align="center" />
        <el-table-column fixed="right" label="操作" width="220">
          <template #default="scope">
            <el-button
              v-hasPerm="['building:buildingPhotos:edit']"
              type="primary"
              size="small"
              link
              @click="handleOpenDialog(scope.row.id)"
            >
              <template #icon><Edit /></template>
              编辑
            </el-button>
            <el-button
              v-hasPerm="['building:buildingPhotos:delete']"
              type="danger"
              size="small"
              link
              @click="handleDelete(scope.row.id)"
            >
              <template #icon><Delete /></template>
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-if="total > 0"
        v-model:total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="handleQuery()"
      />
    </el-card>

    <!-- 建筑物照片表单弹窗 -->
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="500px"
      @close="handleCloseDialog"
    >
      <el-form ref="dataFormRef" :model="formData" :rules="rules" label-width="100px">
        <!-- <el-form-item label="主键ID" prop="id">
          <el-input v-model="formData.id" placeholder="主键ID" />
        </el-form-item> -->

        <!-- <el-form-item label="建筑名称ID" prop="buildingId">
          <el-input v-model="formData.buildingId" placeholder="建筑名称ID" />
        </el-form-item> -->
        <el-form-item label="古建筑名称" prop="buildingId">
          <el-select v-model="formData.buildingId" placeholder="请选择古建筑名称">
            <el-option
              v-for="item in buildingOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="建筑部位" prop="buildingPart">
          <el-input v-model="formData.buildingPart" placeholder="建筑部位" />
        </el-form-item>

        <el-form-item label="照片URL地址" prop="photoUrl">
          <el-input v-model="formData.photoUrl" placeholder="照片URL地址" />
        </el-form-item>

        <el-form-item label="照片描述" prop="description">
          <el-input v-model="formData.description" placeholder="照片描述" />
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-input v-model="formData.status" placeholder="状态" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit()">确定</el-button>
          <el-button @click="handleCloseDialog()">取消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
defineOptions({
  name: "BuildingPhotos",
  inheritAttrs: false,
});

import BuildingPhotosAPI, {
  BuildingPhotosPageVO,
  BuildingPhotosForm,
  BuildingPhotosPageQuery,
} from "@/api/building/building-photos";

import BuildingListAPI from "@/api/building/building-list";
const queryFormRef = ref();
const dataFormRef = ref();

const loading = ref(false);
const removeIds = ref<number[]>([]);
const total = ref(0);
const buildingOptions = ref<OptionType[]>();

const queryParams = reactive<BuildingPhotosPageQuery>({
  pageNum: 1,
  pageSize: 10,
});

// 建筑物照片表格数据
const pageData = ref<BuildingPhotosPageVO[]>([]);

// 弹窗
const dialog = reactive({
  title: "",
  visible: false,
});

// 建筑物照片表单数据
const formData = reactive<BuildingPhotosForm>({});

// 建筑物照片表单校验规则
const rules = reactive({
  id: [{ required: true, message: "请输入主键ID", trigger: "blur" }],
  buildingId: [{ required: true, message: "请输入建筑名称ID", trigger: "blur" }],
});

/** 查询建筑物照片 */
function handleQuery() {
  loading.value = true;
  BuildingPhotosAPI.getPage(queryParams)
    .then((data) => {
      pageData.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置建筑物照片查询 */
function handleResetQuery() {
  queryFormRef.value!.resetFields();
  queryParams.pageNum = 1;
  handleQuery();
}

/** 行复选框选中记录选中ID集合 */
function handleSelectionChange(selection: any) {
  removeIds.value = selection.map((item: any) => item.id);
}

//获取建筑物列表
async function getBuildingOptions() {
  buildingOptions.value = (await BuildingListAPI.getOptions()).map((item) => ({
    ...item,
    value: Number(item.value), // 强制转为数字
  }));
}
/** 打开建筑物照片弹窗 */
async function handleOpenDialog(id?: number) {
  dialog.visible = true;
  if (id) {
    dialog.title = "修改建筑物照片";
    BuildingPhotosAPI.getFormData(id).then((data) => {
      Object.assign(formData, data);
    });
  } else {
    dialog.title = "新增建筑物照片";
  }
}

/** 提交建筑物照片表单 */
function handleSubmit() {
  dataFormRef.value.validate((valid: any) => {
    if (valid) {
      loading.value = true;
      const id = formData.id;
      if (id) {
        BuildingPhotosAPI.update(id, formData)
          .then(() => {
            ElMessage.success("修改成功");
            handleCloseDialog();
            handleResetQuery();
          })
          .finally(() => (loading.value = false));
      } else {
        BuildingPhotosAPI.add(formData)
          .then(() => {
            ElMessage.success("新增成功");
            handleCloseDialog();
            handleResetQuery();
          })
          .finally(() => (loading.value = false));
      }
    }
  });
}

/** 关闭建筑物照片弹窗 */
function handleCloseDialog() {
  dialog.visible = false;
  dataFormRef.value.resetFields();
  dataFormRef.value.clearValidate();
  formData.id = undefined;
}

/** 删除建筑物照片 */
function handleDelete(id?: number) {
  const ids = [id || removeIds.value].join(",");
  if (!ids) {
    ElMessage.warning("请勾选删除项");
    return;
  }

  ElMessageBox.confirm("确认删除已选中的数据项?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(
    () => {
      loading.value = true;
      BuildingPhotosAPI.deleteByIds(ids)
        .then(() => {
          ElMessage.success("删除成功");
          handleResetQuery();
        })
        .finally(() => (loading.value = false));
    },
    () => {
      ElMessage.info("已取消删除");
    }
  );
}

onMounted(() => {
  handleQuery();
  getBuildingOptions();
});
</script>
