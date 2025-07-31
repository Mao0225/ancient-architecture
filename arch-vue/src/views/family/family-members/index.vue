<template>
  <div class="app-container">
    <div class="search-bar">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="用户编号" prop="userId">
          <el-input
            v-model="queryParams.userId"
            placeholder="用户编号"
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
          v-hasPerm="['family:familyMembers:add']"
          type="success"
          @click="handleOpenDialog()"
        >
          <template #icon><Plus /></template>
          新增
        </el-button>
        <el-button
          v-hasPerm="['family:familyMembers:delete']"
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
        <!-- <el-table-column key="id" label="成员ID" prop="id" min-width="150" align="center" /> -->
        <el-table-column key="name" label="成员姓名" prop="name" min-width="100" align="center" />
        <el-table-column key="gender" label="性别" prop="gender" min-width="100" align="center" />
        <el-table-column
          key="birthDate"
          label="出生日期"
          prop="birthDate"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="relationship"
          label="家庭关系"
          prop="relationship"
          min-width="150"
          align="center"
        />
        <el-table-column key="phone" label="联系电话" prop="phone" min-width="100" align="center" />
        <el-table-column key="email" label="电子邮箱" prop="email" min-width="100" align="center" />
        <el-table-column
          key="username"
          label="用户名"
          prop="username"
          min-width="150"
          align="center"
        />
        <el-table-column
          key="password"
          label="密码"
          prop="password"
          min-width="150"
          align="center"
        />

        <el-table-column
          key="userNickname"
          label="所属用户姓名"
          prop="userNickname"
          min-width="150"
          align="center"
        />

        <el-table-column label="登录状态" prop="status">
          <template #default="scope">
            <el-tag :type="scope.row.status === '1' ? 'success' : 'danger'" effect="dark">
              {{ scope.row.status === "1" ? "允许" : "禁止" }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="操作" width="220">
          <template #default="scope">
            <el-button
              v-hasPerm="['family:familyMembers:edit']"
              type="primary"
              size="small"
              link
              @click="handleOpenDialog(scope.row.id)"
            >
              <template #icon><Edit /></template>
              编辑
            </el-button>
            <el-button
              v-hasPerm="['family:familyMembers:delete']"
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

    <!-- 家庭成员信息表单弹窗 -->
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="500px"
      @close="handleCloseDialog"
    >
      <el-form ref="dataFormRef" :model="formData" :rules="rules" label-width="100px">
        <!-- <el-form-item label="成员ID，主键自增" prop="id">
          <el-input v-model="formData.id" placeholder="成员ID，主键自增" />
        </el-form-item> -->

        <el-form-item label="成员姓名" prop="name">
          <el-input v-model="formData.name" placeholder="成员姓名" />
        </el-form-item>

        <el-form-item label="性别" prop="gender">
          <el-input v-model="formData.gender" placeholder="性别：男/女" />
        </el-form-item>

        <el-form-item label="出生日期" prop="birthDate">
          <el-date-picker
            v-model="formData.birthDate"
            class="!w-[240px]"
            type="date"
            placeholder="出生日期"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>

        <el-form-item label="家庭关系" prop="relationship">
          <el-input v-model="formData.relationship" placeholder="家庭关系" />
        </el-form-item>

        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="formData.phone" placeholder="联系电话" />
        </el-form-item>

        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="电子邮箱" />
        </el-form-item>

        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" placeholder="用户名" />
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="formData.password" placeholder="密码" />
        </el-form-item>

        <!-- 登陆状态，1为允许，0为不允许 -->
        <el-form-item label="手机端登录" prop="status">
          <el-select v-model="formData.status" placeholder="请选择登录状态">
            <el-option label="允许" :value="1"></el-option>
            <el-option label="禁止" :value="0"></el-option>
          </el-select>
        </el-form-item>
        <!-- 
        <el-form-item label="所属的用户" prop="userId">
          <el-input v-model="formData.userId" placeholder="所属的用户id" />
        </el-form-item> -->
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
  name: "FamilyMembers",
  inheritAttrs: false,
});

import FamilyMembersAPI, {
  FamilyMembersPageVO,
  FamilyMembersForm,
  FamilyMembersPageQuery,
} from "@/api/family/family-members";

const queryFormRef = ref();
const dataFormRef = ref();

const loading = ref(false);
const removeIds = ref<number[]>([]);
const total = ref(0);

const queryParams = reactive<FamilyMembersPageQuery>({
  userId: null,
  pageNum: 1,
  pageSize: 10,
});

// 家庭成员信息表格数据
const pageData = ref<FamilyMembersPageVO[]>([]);

// 弹窗
const dialog = reactive({
  title: "",
  visible: false,
});

// 家庭成员信息表单数据
const formData = reactive<FamilyMembersForm>({});

// 家庭成员信息表单校验规则
const rules = reactive({
  id: [{ required: true, message: "请输入成员ID，主键自增", trigger: "blur" }],
  name: [{ required: true, message: "请输入成员姓名", trigger: "blur" }],
  gender: [{ required: true, message: "请输入性别：男/女", trigger: "blur" }],
  relationship: [
    {
      required: true,
      message: "请输入家庭关系",
      trigger: "blur",
    },
  ],
  userId: [{ required: true, message: "请输入所属的用户id", trigger: "blur" }],
});

/** 查询家庭成员信息 */
function handleQuery() {
  loading.value = true;
  FamilyMembersAPI.getPage(queryParams)
    .then((data) => {
      pageData.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置家庭成员信息查询 */
function handleResetQuery() {
  queryFormRef.value!.resetFields();
  queryParams.pageNum = 1;
  queryParams.userId = null;
  handleQuery();
}

/** 行复选框选中记录选中ID集合 */
function handleSelectionChange(selection: any) {
  removeIds.value = selection.map((item: any) => item.id);
}

/** 打开家庭成员信息弹窗 */
function handleOpenDialog(id?: number) {
  dialog.visible = true;
  if (id) {
    dialog.title = "修改家庭成员信息";
    FamilyMembersAPI.getFormData(id).then((data) => {
      Object.assign(formData, data);
    });
  } else {
    dialog.title = "新增家庭成员信息";
  }
}

/** 提交家庭成员信息表单 */
function handleSubmit() {
  dataFormRef.value.validate((valid: any) => {
    if (valid) {
      loading.value = true;
      const id = formData.id;
      if (id) {
        FamilyMembersAPI.update(id, formData)
          .then(() => {
            ElMessage.success("修改成功");
            handleCloseDialog();
            handleResetQuery();
          })
          .finally(() => (loading.value = false));
      } else {
        FamilyMembersAPI.add(formData)
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

/** 关闭家庭成员信息弹窗 */
function handleCloseDialog() {
  dialog.visible = false;
  dataFormRef.value.resetFields();
  dataFormRef.value.clearValidate();
  formData.id = undefined;
}

/** 删除家庭成员信息 */
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
      FamilyMembersAPI.deleteByIds(ids)
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
});
</script>
