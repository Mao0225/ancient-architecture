<template>
  <div class="app-container">
    <div class="search-bar">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="建筑物名称" prop="buildingName">
          <el-input
            v-model="queryParams.buildingName"
            placeholder="建筑物名称"
            clearable
            @keyup.enter="handleQuery()"
          />
        </el-form-item>
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
          v-hasPerm="['building:buildingList:add']"
          type="success"
          @click="handleOpenBuildingDialog()"
        >
          <template #icon><Plus /></template>
          新增建筑
        </el-button>
        <el-button
          v-hasPerm="['building:buildingList:delete']"
          type="danger"
          :disabled="selectedBuildings.length === 0"
          @click="handleDeleteBuildings()"
        >
          <template #icon><Delete /></template>
          批量删除
        </el-button>
      </div>

      <!-- 卡片式建筑列表 -->
      <div v-loading="loading" class="building-cards">
        <div
          v-for="building in pageData"
          :key="building.id"
          class="building-card"
          :class="{ selected: selectedBuildings.includes(building.id) }"
          @click="toggleSelectBuilding(building.id)"
        >
          <el-card shadow="hover" class="card-item">
            <div class="card-header">
              <el-checkbox
                :model-value="selectedBuildings.includes(building.id)"
                @change="toggleSelectBuilding(building.id)"
                @click.stop
              />
              <h3 class="building-title">{{ building.buildingName }}</h3>
            </div>

            <div class="card-content">
              <p class="building-description">{{ building.buildingDescription || "暂无描述" }}</p>
              <p class="create-time">创建时间: {{ building.createTime }}</p>
            </div>

            <div class="card-actions">
              <el-button
                v-hasPerm="['building:buildingPhotos:view']"
                type="primary"
                size="small"
                @click.stop="handleOpenPhotosDialog(building.id, building.buildingName)"
              >
                <template #icon><Picture /></template>
                管理图片
              </el-button>
              <el-button
                v-hasPerm="['building:buildingList:edit']"
                type="warning"
                size="small"
                @click.stop="handleOpenBuildingDialog(building.id)"
              >
                <template #icon><Edit /></template>
                编辑
              </el-button>
              <el-button
                v-hasPerm="['building:buildingList:delete']"
                type="danger"
                size="small"
                @click.stop="handleDeleteBuildings(building.id)"
              >
                <template #icon><Delete /></template>
                删除
              </el-button>
            </div>
          </el-card>
        </div>
      </div>

      <!-- 空状态 -->
      <el-empty v-if="!loading && pageData.length === 0" description="暂无建筑数据" />

      <!-- 分页 -->
      <pagination
        v-if="total > 0"
        v-model:total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="handleQuery()"
      />
    </el-card>

    <!-- 建筑物表单弹窗 -->
    <el-dialog
      v-model="buildingDialog.visible"
      :title="buildingDialog.title"
      width="500px"
      @close="handleCloseBuildingDialog"
    >
      <el-form
        ref="buildingFormRef"
        :model="buildingFormData"
        :rules="buildingRules"
        label-width="100px"
      >
        <el-form-item label="建筑物名称" prop="buildingName">
          <el-input v-model="buildingFormData.buildingName" placeholder="建筑物名称" />
        </el-form-item>
        <el-form-item label="详细描述" prop="buildingDescription">
          <el-input
            v-model="buildingFormData.buildingDescription"
            type="textarea"
            :rows="4"
            placeholder="详细描述"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmitBuilding">确定</el-button>
          <el-button @click="handleCloseBuildingDialog">取消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 建筑物图片管理弹窗 -->
    <el-dialog
      v-model="photosDialog.visible"
      :title="photosDialog.title"
      width="1200px"
      @close="handleClosePhotosDialog"
    >
      <div class="photos-management">
        <div class="photos-header">
          <el-button
            v-hasPerm="['building:buildingPhotos:add']"
            type="success"
            @click="handleOpenPhotoDialog()"
          >
            <template #icon><Plus /></template>
            新增图片
          </el-button>
          <el-button
            v-hasPerm="['building:buildingPhotos:delete']"
            type="danger"
            :disabled="selectedPhotos.length === 0"
            @click="handleDeletePhotos()"
          >
            <template #icon><Delete /></template>
            删除选中
          </el-button>
        </div>

        <el-table
          ref="photosTableRef"
          v-loading="photosLoading"
          :data="photosData"
          highlight-current-row
          border
          @selection-change="handlePhotosSelectionChange"
        >
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="建筑部位" prop="buildingPart" min-width="120" align="center" />
          <el-table-column label="照片预览" prop="photoUrl" width="120" align="center">
            <template #default="scope">
              <el-image
                :src="`${baseUrl}${scope.row.photoUrl}`"
                :preview-src-list="[`${baseUrl}${scope.row.photoUrl}`]"
                fit="cover"
                style="width: 80px; height: 60px; border-radius: 4px"
                preview-teleported
                loading="lazy"
                :lazy="true"
              />
            </template>
          </el-table-column>
          <el-table-column label="照片描述" prop="description" min-width="150" align="center" />
          <el-table-column label="状态" prop="status" width="80" align="center">
            <template #default="scope">
              <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                {{ scope.row.status === 1 ? "正常" : "禁用" }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createTime" width="150" align="center" />
          <el-table-column fixed="right" label="操作" width="180">
            <template #default="scope">
              <el-button
                v-hasPerm="['building:buildingPhotos:edit']"
                type="primary"
                size="small"
                link
                @click="handleOpenPhotoDialog(scope.row.id)"
              >
                <template #icon><Edit /></template>
                编辑
              </el-button>
              <el-button
                v-hasPerm="['building:buildingPhotos:delete']"
                type="danger"
                size="small"
                link
                @click="handleDeletePhotos(scope.row.id)"
              >
                <template #icon><Delete /></template>
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-if="photosTotal > 0"
          v-model:total="photosTotal"
          v-model:page="photosQueryParams.pageNum"
          v-model:limit="photosQueryParams.pageSize"
          @pagination="handleQueryPhotos()"
        />
      </div>
    </el-dialog>

    <!-- 图片表单弹窗 -->
    <el-dialog
      v-model="photoDialog.visible"
      :title="photoDialog.title"
      width="500px"
      @close="handleClosePhotoDialog"
    >
      <el-form ref="photoFormRef" :model="photoFormData" :rules="photoRules" label-width="100px">
        <el-form-item label="建筑部位" prop="buildingPart">
          <el-input v-model="photoFormData.buildingPart" placeholder="建筑部位" />
        </el-form-item>
        <el-form-item label="照片" prop="photoUrl">
          <div class="photo-upload-container">
            <div v-if="photoFormData.photoUrl" class="photo-preview">
              <el-image
                :src="`${baseUrl}${photoFormData.photoUrl}`"
                fit="cover"
                style="width: 120px; height: 120px; border-radius: 8px"
                :preview-src-list="[`${baseUrl}${photoFormData.photoUrl}`]"
                preview-teleported
              />
              <el-button
                type="info"
                class="upload-btn"
                circle
                size="small"
                @click="triggerPhotoUpload"
              />
            </div>
            <div v-else class="photo-placeholder" @click="triggerPhotoUpload">
              <el-icon :size="40" color="#ccc"><Picture /></el-icon>
              <p>点击上传照片</p>
            </div>
            <input
              ref="photoFileInput"
              type="file"
              style="display: none"
              accept="image/*"
              @change="handlePhotoFileChange"
            />
          </div>
        </el-form-item>
        <el-form-item label="照片描述" prop="description">
          <el-input
            v-model="photoFormData.description"
            type="textarea"
            :rows="3"
            placeholder="照片描述"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="photoFormData.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmitPhoto">确定</el-button>
          <el-button @click="handleClosePhotoDialog">取消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
defineOptions({
  name: "BuildingList",
  inheritAttrs: false,
});

import BuildingListAPI, {
  BuildingListPageVO,
  BuildingListForm,
  BuildingListPageQuery,
} from "@/api/building/building-list";
// import { ElForm } from "element-plus";
import BuildingPhotosAPI, {
  BuildingPhotosPageVO,
  BuildingPhotosForm,
  BuildingPhotosPageQuery,
} from "@/api/building/building-photos";

import FileAPI from "@/api/file.api"; // 添加文件上传API导入
// import { loadEnv } from "vite";
// const env = loadEnv("dev", process.cwd()); // 直接指定模式为 development

const baseUrl = import.meta.env.VITE_APP_BASE_API;

const queryFormRef = ref();
const buildingFormRef = ref();
const photoFormRef = ref();
const photosTableRef = ref();
const photoFileInput = ref<HTMLInputElement>(); // 添加照片文件输入引用

const loading = ref(false);
const photosLoading = ref(false);
const selectedBuildings = ref<number[]>([]);
const selectedPhotos = ref<number[]>([]);
const total = ref(0);
const photosTotal = ref(0);
const currentBuildingId = ref<number>();

// 建筑物查询参数
const queryParams = reactive<BuildingListPageQuery>({
  pageNum: 1,
  pageSize: 10,
  buildingName: "",
});

// 图片查询参数
const photosQueryParams = reactive<BuildingPhotosPageQuery>({
  pageNum: 1,
  pageSize: 10,
  buildingId: undefined,
});

// 建筑物表格数据
const pageData = ref<BuildingListPageVO[]>([]);
// 图片表格数据
const photosData = ref<BuildingPhotosPageVO[]>([]);

// 建筑物弹窗
const buildingDialog = reactive({
  title: "",
  visible: false,
});

// 图片管理弹窗
const photosDialog = reactive({
  title: "",
  visible: false,
});

// 图片表单弹窗
const photoDialog = reactive({
  title: "",
  visible: false,
});

// 建筑物表单数据
const buildingFormData = reactive<BuildingListForm>({});

// 图片表单数据
const photoFormData = reactive<BuildingPhotosForm>({});

// 建筑物表单校验规则
const buildingRules = reactive({
  buildingName: [{ required: true, message: "请输入建筑物名称", trigger: "blur" }],
});

// 图片表单校验规则
const photoRules = reactive({
  buildingPart: [{ required: true, message: "请输入建筑部位", trigger: "blur" }],
  photoUrl: [{ required: true, message: "请输入照片URL地址", trigger: "blur" }],
  status: [{ required: true, message: "请选择状态", trigger: "change" }],
});

/** 查询建筑物列表 */
function handleQuery() {
  loading.value = true;
  BuildingListAPI.getPage(queryParams)
    .then((data) => {
      pageData.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置建筑物查询 */
function handleResetQuery() {
  queryFormRef.value!.resetFields();
  queryParams.pageNum = 1;
  queryParams.buildingName = "";
  selectedBuildings.value = [];
  handleQuery();
}

/** 切换选中建筑 */
function toggleSelectBuilding(id: number) {
  const index = selectedBuildings.value.indexOf(id);
  if (index > -1) {
    selectedBuildings.value.splice(index, 1);
  } else {
    selectedBuildings.value.push(id);
  }
}

/** 打开建筑物弹窗 */
function handleOpenBuildingDialog(id?: number) {
  buildingDialog.visible = true;
  if (id) {
    buildingDialog.title = "修改建筑物";
    BuildingListAPI.getFormData(id).then((data) => {
      Object.assign(buildingFormData, data);
    });
  } else {
    buildingDialog.title = "新增建筑物";
  }
}

/** 提交建筑物表单 */
function handleSubmitBuilding() {
  buildingFormRef.value.validate((valid: any) => {
    if (valid) {
      loading.value = true;
      const id = buildingFormData.id;
      if (id) {
        BuildingListAPI.update(id, buildingFormData)
          .then(() => {
            ElMessage.success("修改成功");
            handleCloseBuildingDialog();
            handleQuery();
          })
          .finally(() => (loading.value = false));
      } else {
        BuildingListAPI.add(buildingFormData)
          .then(() => {
            ElMessage.success("新增成功");
            handleCloseBuildingDialog();
            handleQuery();
          })
          .finally(() => (loading.value = false));
      }
    }
  });
}

/** 关闭建筑物弹窗 */
function handleCloseBuildingDialog() {
  buildingDialog.visible = false;
  buildingFormRef.value.resetFields();
  buildingFormRef.value.clearValidate();
  buildingFormData.id = undefined;
}

/** 删除建筑物 */
function handleDeleteBuildings(id?: number) {
  const ids = id ? [id] : selectedBuildings.value;
  if (ids.length === 0) {
    ElMessage.warning("请选择要删除的建筑物");
    return;
  }

  ElMessageBox.confirm("确认删除已选中的建筑物?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    loading.value = true;
    BuildingListAPI.deleteByIds(ids.join(","))
      .then(() => {
        ElMessage.success("删除成功");
        selectedBuildings.value = [];
        handleQuery();
      })
      .finally(() => (loading.value = false));
  });
}

/** 打开图片管理弹窗 */
function handleOpenPhotosDialog(buildingId: number, buildingName: string) {
  currentBuildingId.value = buildingId;
  photosDialog.title = `${buildingName} - 图片管理`;
  photosDialog.visible = true;
  photosQueryParams.buildingId = buildingId;
  photosQueryParams.pageNum = 1;
  handleQueryPhotos();
}

/** 关闭图片管理弹窗 */
function handleClosePhotosDialog() {
  photosDialog.visible = false;
  selectedPhotos.value = [];
  photosData.value = [];
  currentBuildingId.value = undefined;
}

/** 查询图片列表 */
function handleQueryPhotos() {
  photosLoading.value = true;
  BuildingPhotosAPI.getPage(photosQueryParams)
    .then((data) => {
      photosData.value = data.list;
      photosTotal.value = data.total;
    })
    .finally(() => {
      photosLoading.value = false;
    });
}

/** 图片选择变化 */
function handlePhotosSelectionChange(selection: any) {
  selectedPhotos.value = selection.map((item: any) => item.id);
}

/** 打开图片表单弹窗 */
function handleOpenPhotoDialog(id?: number) {
  photoDialog.visible = true;
  if (id) {
    photoDialog.title = "修改图片";
    BuildingPhotosAPI.getFormData(id).then((data) => {
      Object.assign(photoFormData, data);
    });
  } else {
    photoDialog.title = "新增图片";
    photoFormData.buildingId = currentBuildingId.value;
    photoFormData.status = 1; // 默认状态为正常
  }
}

/** 提交图片表单 */
function handleSubmitPhoto() {
  photoFormRef.value.validate((valid: any) => {
    if (valid) {
      photosLoading.value = true;
      const id = photoFormData.id;
      if (id) {
        BuildingPhotosAPI.update(id, photoFormData)
          .then(() => {
            ElMessage.success("修改成功");
            handleClosePhotoDialog();
            handleQueryPhotos();
          })
          .finally(() => (photosLoading.value = false));
      } else {
        BuildingPhotosAPI.add(photoFormData)
          .then(() => {
            ElMessage.success("新增成功");
            handleClosePhotoDialog();
            handleQueryPhotos();
          })
          .finally(() => (photosLoading.value = false));
      }
    }
  });
}

/** 关闭图片表单弹窗 */
function handleClosePhotoDialog() {
  photoDialog.visible = false;
  photoFormRef.value.resetFields();
  photoFormRef.value.clearValidate();
  photoFormData.id = undefined;
  photoFormData.buildingId = undefined;
}

/** 删除图片 */
function handleDeletePhotos(id?: number) {
  const ids = id ? [id] : selectedPhotos.value;
  if (ids.length === 0) {
    ElMessage.warning("请选择要删除的图片");
    return;
  }

  ElMessageBox.confirm("确认删除已选中的图片?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    photosLoading.value = true;
    BuildingPhotosAPI.deleteByIds(ids.join(","))
      .then(() => {
        ElMessage.success("删除成功");
        selectedPhotos.value = [];
        handleQueryPhotos();
      })
      .finally(() => (photosLoading.value = false));
  });
}

/** 触发照片上传 */
function triggerPhotoUpload() {
  photoFileInput.value?.click();
}

/** 处理照片文件上传 */
async function handlePhotoFileChange(event: Event) {
  const target = event.target as HTMLInputElement;
  const file = target.files ? target.files[0] : null;
  if (file) {
    try {
      // 显示上传进度
      ElMessage.info("正在上传照片...");

      // 调用文件上传API
      const data = await FileAPI.uploadFile(file);

      // 更新表单中的照片URL
      photoFormData.photoUrl = data.url;

      ElMessage.success("照片上传成功");
    } catch (error) {
      console.error("照片上传失败：" + error);
      ElMessage.error("照片上传失败");
    }

    // 清空文件输入，允许重复选择同一文件
    target.value = "";
  }
}

onMounted(() => {
  handleQuery();
});
</script>

<style scoped>
.building-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 20px;
  margin-bottom: 20px;
}

.building-card {
  cursor: pointer;
  transition: all 0.3s ease;
}

.building-card.selected .card-item {
  border-color: var(--el-color-primary);
  box-shadow: 0 0 0 2px var(--el-color-primary-light-8);
}

.card-item {
  height: 100%;
  transition: all 0.3s ease;
}

.card-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.card-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
  padding-bottom: 12px;
  border-bottom: 1px solid var(--el-border-color-lighter);
}

.building-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: var(--el-text-color-primary);
  flex: 1;
}

.card-content {
  margin-bottom: 20px;
}

.building-description {
  margin: 0 0 12px 0;
  color: var(--el-text-color-regular);
  line-height: 1.5;
  font-size: 14px;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.create-time {
  margin: 0;
  font-size: 12px;
  color: var(--el-text-color-placeholder);
}

.card-actions {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.photos-management {
  padding: 0;
}

.photos-header {
  margin-bottom: 16px;
  display: flex;
  gap: 12px;
}

.el-empty {
  padding: 60px 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .building-cards {
    grid-template-columns: 1fr;
    gap: 16px;
  }

  .card-actions {
    justify-content: center;
  }

  .card-actions .el-button {
    flex: 1;
    min-width: 0;
  }
}

/* 照片上传样式 */
.photo-upload-container {
  display: flex;
  justify-content: center;
}

.photo-preview {
  position: relative;
  display: inline-block;
}

.upload-btn {
  position: absolute;
  bottom: 8px;
  right: 8px;
  cursor: pointer;
}

.photo-placeholder {
  width: 120px;
  height: 120px;
  border: 2px dashed var(--el-border-color);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s ease;
  background-color: var(--el-fill-color-light);
}

.photo-placeholder:hover {
  border-color: var(--el-color-primary);
  background-color: var(--el-color-primary-light-9);
}

.photo-placeholder p {
  margin: 8px 0 0 0;
  font-size: 12px;
  color: var(--el-text-color-placeholder);
}
</style>
