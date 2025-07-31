<template>
  <div class="dashboard-container">
    <!-- github 角标 -->
    <!-- <github-corner class="github-corner" /> -->

    <el-card shadow="never" class="mt-2">
      <el-row class="h-80px">
        <el-col :span="18" :xs="24">
          <div class="flex-x-start">
            <img class="w80px h80px rounded-full" :src="`${baseUrl}${userStore.userInfo.avatar}`" />

            <div class="ml-5">
              <p>{{ greetings }}</p>
              <p class="text-sm text-gray">
                智能家居监控系统运行正常，设备在线率：{{ deviceOnlineRate }}%
              </p>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <!-- 系统数据统计 -->
    <el-row :gutter="10" class="mt-5">
      <!-- 家庭数量 -->
      <el-col :span="6">
        <el-skeleton :loading="systemStatsLoading" :rows="5" animated>
          <template #template>
            <el-card>
              <template #header>
                <div>
                  <el-skeleton-item variant="h3" style="width: 40%" />
                  <el-skeleton-item variant="rect" style="float: right; width: 1em; height: 1em" />
                </div>
              </template>

              <div class="flex-x-between">
                <el-skeleton-item variant="text" style="width: 30%" />
                <el-skeleton-item variant="circle" style="width: 2em; height: 2em" />
              </div>
              <div class="mt-5 flex-x-between">
                <el-skeleton-item variant="text" style="width: 50%" />
                <el-skeleton-item variant="text" style="width: 1em" />
              </div>
            </el-card>
          </template>
          <template v-if="!systemStatsLoading">
            <el-card shadow="never">
              <template #header>
                <div class="flex-x-between">
                  <span class="text-gray">家庭数量</span>
                  <el-tag type="success" size="small">实时</el-tag>
                </div>
              </template>

              <div class="flex-x-between mt-2">
                <div class="flex-y-center">
                  <span class="text-lg">{{ systemStatsData.familyCount }}</span>
                  <span
                    :class="[
                      'text-xs',
                      'ml-2',
                      computeGrowthRateClass(systemStatsData.familyGrowthRate),
                    ]"
                  >
                    <el-icon>
                      <Top v-if="systemStatsData.familyGrowthRate > 0" />
                      <Bottom v-else-if="systemStatsData.familyGrowthRate < 0" />
                    </el-icon>
                    {{ formatGrowthRate(systemStatsData.familyGrowthRate) }}
                  </span>
                </div>
                <div class="i-svg:home w-8 h-8" />
              </div>

              <div class="flex-x-between mt-2 text-sm text-gray">
                <span>活跃家庭</span>
                <span>{{ systemStatsData.activeFamilyCount }}</span>
              </div>
            </el-card>
          </template>
        </el-skeleton>
      </el-col>

      <!-- 摄像头数量 -->
      <el-col :span="6">
        <el-skeleton :loading="systemStatsLoading" :rows="5" animated>
          <template #template>
            <el-card>
              <template #header>
                <div>
                  <el-skeleton-item variant="h3" style="width: 40%" />
                  <el-skeleton-item variant="rect" style="float: right; width: 1em; height: 1em" />
                </div>
              </template>

              <div class="flex-x-between">
                <el-skeleton-item variant="text" style="width: 30%" />
                <el-skeleton-item variant="circle" style="width: 2em; height: 2em" />
              </div>
              <div class="mt-5 flex-x-between">
                <el-skeleton-item variant="text" style="width: 50%" />
                <el-skeleton-item variant="text" style="width: 1em" />
              </div>
            </el-card>
          </template>
          <template v-if="!systemStatsLoading">
            <el-card shadow="never">
              <template #header>
                <div class="flex-x-between">
                  <span class="text-gray">摄像头数量</span>
                  <el-tag type="primary" size="small">实时</el-tag>
                </div>
              </template>

              <div class="flex-x-between mt-2">
                <div class="flex-y-center">
                  <span class="text-lg">{{ systemStatsData.cameraCount }}</span>
                  <span
                    :class="[
                      'text-xs',
                      'ml-2',
                      systemStatsData.onlineCameraCount === systemStatsData.cameraCount
                        ? 'color-[--el-color-success]'
                        : 'color-[--el-color-warning]',
                    ]"
                  >
                    <el-icon>
                      <VideoCamera />
                    </el-icon>
                    在线{{ systemStatsData.onlineCameraCount }}台
                  </span>
                </div>
                <div class="i-svg:camera w-8 h-8" />
              </div>

              <div class="flex-x-between mt-2 text-sm text-gray">
                <span>今日新增</span>
                <span>{{ systemStatsData.todayNewCameraCount }}</span>
              </div>
            </el-card>
          </template>
        </el-skeleton>
      </el-col>

      <!-- 树莓派数量 -->
      <el-col :span="6">
        <el-skeleton :loading="systemStatsLoading" :rows="5" animated>
          <template #template>
            <el-card>
              <template #header>
                <div>
                  <el-skeleton-item variant="h3" style="width: 40%" />
                  <el-skeleton-item variant="rect" style="float: right; width: 1em; height: 1em" />
                </div>
              </template>

              <div class="flex-x-between">
                <el-skeleton-item variant="text" style="width: 30%" />
                <el-skeleton-item variant="circle" style="width: 2em; height: 2em" />
              </div>
              <div class="mt-5 flex-x-between">
                <el-skeleton-item variant="text" style="width: 50%" />
                <el-skeleton-item variant="text" style="width: 1em" />
              </div>
            </el-card>
          </template>
          <template v-if="!systemStatsLoading">
            <el-card shadow="never">
              <template #header>
                <div class="flex-x-between">
                  <span class="text-gray">树莓派设备</span>
                  <el-tag type="warning" size="small">实时</el-tag>
                </div>
              </template>

              <div class="flex-x-between mt-2">
                <div class="flex-y-center">
                  <span class="text-lg">{{ systemStatsData.raspberryPiCount }}</span>
                  <span
                    :class="[
                      'text-xs',
                      'ml-2',
                      systemStatsData.onlineRaspberryPiCount === systemStatsData.raspberryPiCount
                        ? 'color-[--el-color-success]'
                        : 'color-[--el-color-warning]',
                    ]"
                  >
                    <el-icon>
                      <Monitor />
                    </el-icon>
                    在线{{ systemStatsData.onlineRaspberryPiCount }}台
                  </span>
                </div>
                <div class="i-svg:raspberry-pi w-8 h-8" />
              </div>

              <div class="flex-x-between mt-2 text-sm text-gray">
                <span>CPU平均负载</span>
                <span>{{ systemStatsData.avgCpuLoad }}%</span>
              </div>
            </el-card>
          </template>
        </el-skeleton>
      </el-col>

      <!-- 今日抓拍次数 -->
      <el-col :span="6">
        <el-skeleton :loading="systemStatsLoading" :rows="5" animated>
          <template #template>
            <el-card>
              <template #header>
                <div>
                  <el-skeleton-item variant="h3" style="width: 40%" />
                  <el-skeleton-item variant="rect" style="float: right; width: 1em; height: 1em" />
                </div>
              </template>

              <div class="flex-x-between">
                <el-skeleton-item variant="text" style="width: 30%" />
                <el-skeleton-item variant="circle" style="width: 2em; height: 2em" />
              </div>
              <div class="mt-5 flex-x-between">
                <el-skeleton-item variant="text" style="width: 50%" />
                <el-skeleton-item variant="text" style="width: 1em" />
              </div>
            </el-card>
          </template>
          <template v-if="!systemStatsLoading">
            <el-card shadow="never">
              <template #header>
                <div class="flex-x-between">
                  <span class="text-gray">今日抓拍</span>
                  <el-tag type="danger" size="small">日</el-tag>
                </div>
              </template>

              <div class="flex-x-between mt-2">
                <div class="flex-y-center">
                  <span class="text-lg">{{ systemStatsData.todayCaptureCount }}</span>
                  <span
                    :class="[
                      'text-xs',
                      'ml-2',
                      computeGrowthRateClass(systemStatsData.captureGrowthRate),
                    ]"
                  >
                    <el-icon>
                      <Top v-if="systemStatsData.captureGrowthRate > 0" />
                      <Bottom v-else-if="systemStatsData.captureGrowthRate < 0" />
                    </el-icon>
                    {{ formatGrowthRate(systemStatsData.captureGrowthRate) }}
                  </span>
                </div>
                <div class="i-svg:capture w-8 h-8" />
              </div>

              <div class="flex-x-between mt-2 text-sm text-gray">
                <span>总抓拍数</span>
                <span>{{ systemStatsData.totalCaptureCount }}</span>
              </div>
            </el-card>
          </template>
        </el-skeleton>
      </el-col>
    </el-row>

    <el-row :gutter="10" class="mt-5">
      <!-- 监控趋势统计图 -->
      <el-col :xs="24" :span="16">
        <el-card>
          <template #header>
            <div class="flex-x-between">
              <span>监控数据趋势</span>
              <el-radio-group v-model="monitorTrendDateRange" size="small">
                <el-radio-button label="近7天" :value="7" />
                <el-radio-button label="近30天" :value="30" />
              </el-radio-group>
            </div>
          </template>
          <ECharts :options="monitorTrendChartOptions" height="400px" />
        </el-card>
      </el-col>
      <!-- 系统动态 -->
      <el-col :xs="24" :span="8">
        <el-card>
          <template #header>
            <div class="flex-x-between">
              <span class="header-title">系统动态</span>
              <el-link type="primary" :underline="false" href="#" target="_blank">
                查看更多
                <el-icon class="link-icon"><TopRight /></el-icon>
              </el-link>
            </div>
          </template>

          <el-scrollbar height="400px">
            <el-timeline class="p-3">
              <el-timeline-item
                v-for="(item, index) in systemActivityList"
                :key="index"
                :timestamp="item.time"
                placement="top"
                :color="getActivityColor(item.type)"
                :hollow="index !== 0"
                size="large"
              >
                <div class="activity-item" :class="{ 'latest-item': index === 0 }">
                  <div>
                    <el-text tag="strong">{{ item.title }}</el-text>
                    <el-tag :type="getActivityTagType(item.type)" size="small">
                      {{ item.type }}
                    </el-tag>
                  </div>

                  <el-text class="activity-content">{{ item.content }}</el-text>

                  <div v-if="item.location">
                    <el-text class="activity-location" type="info">
                      <el-icon><Location /></el-icon>
                      {{ item.location }}
                    </el-text>
                  </div>
                </div>
              </el-timeline-item>
            </el-timeline>
          </el-scrollbar>
        </el-card>
      </el-col>
    </el-row>

    <!-- 设备状态监控 -->
    <el-row :gutter="10" class="mt-5">
      <el-col :span="24">
        <el-card>
          <template #header>
            <div class="flex-x-between">
              <span>设备状态监控</span>
              <el-button type="primary" size="small" @click="refreshDeviceStatus">
                <el-icon><Refresh /></el-icon>
                刷新状态
              </el-button>
            </div>
          </template>

          <el-row :gutter="20">
            <el-col :span="8">
              <div class="device-status-card">
                <h4>摄像头状态分布</h4>
                <ECharts :options="cameraStatusChartOptions" height="200px" />
              </div>
            </el-col>
            <el-col :span="8">
              <div class="device-status-card">
                <h4>树莓派负载分布</h4>
                <ECharts :options="raspberryPiLoadChartOptions" height="200px" />
              </div>
            </el-col>
            <el-col :span="8">
              <div class="device-status-card">
                <h4>报警类型统计</h4>
                <ECharts :options="alarmTypeChartOptions" height="200px" />
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
defineOptions({
  name: "SmartHomeDashboard",
  inheritAttrs: false,
});

import { dayjs } from "element-plus";
import { useUserStore } from "@/store/modules/user.store";
import { formatGrowthRate } from "@/utils";
const baseUrl = import.meta.env.VITE_APP_BASE_API;

interface SystemStatsVO {
  familyCount: number;
  familyGrowthRate: number;
  activeFamilyCount: number;
  cameraCount: number;
  onlineCameraCount: number;
  todayNewCameraCount: number;
  raspberryPiCount: number;
  onlineRaspberryPiCount: number;
  avgCpuLoad: number;
  todayCaptureCount: number;
  captureGrowthRate: number;
  totalCaptureCount: number;
}

interface SystemActivity {
  id: string;
  title: string;
  time: string;
  content: string;
  type: "报警" | "设备" | "用户" | "系统";
  location?: string;
}

interface MonitorTrendVO {
  dates: string[];
  captureList: number[];
  alarmList: number[];
  deviceOnlineList: number[];
}

const userStore = useUserStore();

// 当前系统活动列表
const systemActivityList = ref<SystemActivity[]>([
  {
    id: "1",
    title: "入侵检测报警",
    time: "2025-05-30 14:30:25",
    content: "客厅摄像头检测到异常人员入侵，已触发报警并发送通知。",
    type: "报警",
    location: "张家客厅摄像头01",
  },
  {
    id: "2",
    title: "新设备上线",
    time: "2025-05-30 12:15:10",
    content: "树莓派设备RPi-Kitchen-02成功连接到系统，设备状态正常。",
    type: "设备",
    location: "李家厨房",
  },
  {
    id: "3",
    title: "用户登录",
    time: "2025-05-30 09:45:30",
    content: "用户'张三'通过手机APP成功登录系统。",
    type: "用户",
  },
  {
    id: "4",
    title: "系统维护完成",
    time: "2025-05-30 02:00:00",
    content: "系统定期维护任务完成，所有服务恢复正常运行。",
    type: "系统",
  },
  {
    id: "5",
    title: "异常动作检测",
    time: "2025-05-29 22:18:45",
    content: "卧室摄像头检测到异常动作，疑似老人跌倒，已发送紧急通知。",
    type: "报警",
    location: "王家主卧摄像头03",
  },
]);

// 设备在线率
const deviceOnlineRate = computed(() => {
  if (systemStatsData.value.cameraCount === 0 && systemStatsData.value.raspberryPiCount === 0) {
    return 0;
  }
  const totalDevices = systemStatsData.value.cameraCount + systemStatsData.value.raspberryPiCount;
  const onlineDevices =
    systemStatsData.value.onlineCameraCount + systemStatsData.value.onlineRaspberryPiCount;
  return Math.round((onlineDevices / totalDevices) * 100);
});

// 当前时间（用于计算问候语）
const currentDate = new Date();

// 问候语：根据当前小时返回不同问候语
const greetings = computed(() => {
  const hours = currentDate.getHours();
  const nickname = userStore.userInfo.nickname;
  if (hours >= 6 && hours < 8) {
    return "早安！智能家居守护您的美好一天🌅";
  } else if (hours >= 8 && hours < 12) {
    return `上午好，${nickname}！您的家庭安全由我们守护！`;
  } else if (hours >= 12 && hours < 18) {
    return `下午好，${nickname}！智能监控，安心生活！`;
  } else if (hours >= 18 && hours < 24) {
    return `晚上好，${nickname}！夜晚监控已开启，请安心休息！`;
  } else {
    return "深夜时光，智能守护不间断，祝您好梦🌛！";
  }
});

// 系统统计数据加载状态
const systemStatsLoading = ref(true);
// 系统统计数据
const systemStatsData = ref<SystemStatsVO>({
  familyCount: 156,
  familyGrowthRate: 12.5,
  activeFamilyCount: 148,
  cameraCount: 342,
  onlineCameraCount: 338,
  todayNewCameraCount: 5,
  raspberryPiCount: 178,
  onlineRaspberryPiCount: 172,
  avgCpuLoad: 35.8,
  todayCaptureCount: 1247,
  captureGrowthRate: -5.2,
  totalCaptureCount: 89456,
});

// 监控趋势日期范围（单位：天）
const monitorTrendDateRange = ref(7);
// 监控趋势图表配置
const monitorTrendChartOptions = ref();
// 摄像头状态图表配置
const cameraStatusChartOptions = ref();
// 树莓派负载图表配置
const raspberryPiLoadChartOptions = ref();
// 报警类型图表配置
const alarmTypeChartOptions = ref();

/**
 * 获取系统统计数据
 */
const fetchSystemStatsData = () => {
  // 模拟API调用
  setTimeout(() => {
    systemStatsLoading.value = false;
  }, 1000);
};

/**
 * 获取监控趋势数据，并更新图表配置
 */
const fetchMonitorTrendData = () => {
  const startDate = dayjs()
    .subtract(monitorTrendDateRange.value - 1, "day")
    .toDate();
  const endDate = new Date();

  // 模拟数据
  const mockData: MonitorTrendVO = {
    dates: [],
    captureList: [],
    alarmList: [],
    deviceOnlineList: [],
  };

  // 生成模拟日期和数据
  for (let i = monitorTrendDateRange.value - 1; i >= 0; i--) {
    const date = dayjs().subtract(i, "day").format("MM-DD");
    mockData.dates.push(date);
    mockData.captureList.push(Math.floor(Math.random() * 200) + 800);
    mockData.alarmList.push(Math.floor(Math.random() * 20) + 5);
    mockData.deviceOnlineList.push(Math.floor(Math.random() * 10) + 95);
  }

  updateMonitorTrendChartOptions(mockData);
};

/**
 * 更新监控趋势图表的配置项
 */
const updateMonitorTrendChartOptions = (data: MonitorTrendVO) => {
  monitorTrendChartOptions.value = {
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["抓拍次数", "报警次数", "设备在线率(%)"],
      bottom: 0,
    },
    grid: {
      left: "1%",
      right: "5%",
      bottom: "10%",
      containLabel: true,
    },
    xAxis: {
      type: "category",
      data: data.dates,
    },
    yAxis: [
      {
        type: "value",
        name: "次数",
        splitLine: {
          show: true,
          lineStyle: {
            type: "dashed",
          },
        },
      },
      {
        type: "value",
        name: "在线率(%)",
        min: 0,
        max: 100,
        splitLine: {
          show: false,
        },
      },
    ],
    series: [
      {
        name: "抓拍次数",
        type: "line",
        data: data.captureList,
        areaStyle: {
          color: "rgba(64, 158, 255, 0.1)",
        },
        smooth: true,
        itemStyle: {
          color: "#4080FF",
        },
        lineStyle: {
          color: "#4080FF",
        },
      },
      {
        name: "报警次数",
        type: "line",
        data: data.alarmList,
        areaStyle: {
          color: "rgba(245, 108, 108, 0.1)",
        },
        smooth: true,
        itemStyle: {
          color: "#F56C6C",
        },
        lineStyle: {
          color: "#F56C6C",
        },
      },
      {
        name: "设备在线率(%)",
        type: "line",
        yAxisIndex: 1,
        data: data.deviceOnlineList,
        smooth: true,
        itemStyle: {
          color: "#67C23A",
        },
        lineStyle: {
          color: "#67C23A",
        },
      },
    ],
  };
};

/**
 * 初始化设备状态图表
 */
const initDeviceStatusCharts = () => {
  // 摄像头状态分布
  cameraStatusChartOptions.value = {
    tooltip: {
      trigger: "item",
    },
    series: [
      {
        type: "pie",
        radius: "60%",
        data: [
          {
            value: systemStatsData.value.onlineCameraCount,
            name: "在线",
            itemStyle: { color: "#67C23A" },
          },
          {
            value: systemStatsData.value.cameraCount - systemStatsData.value.onlineCameraCount,
            name: "离线",
            itemStyle: { color: "#F56C6C" },
          },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };

  // 树莓派负载分布
  raspberryPiLoadChartOptions.value = {
    tooltip: {
      trigger: "item",
    },
    series: [
      {
        type: "pie",
        radius: "60%",
        data: [
          { value: 45, name: "低负载(<30%)", itemStyle: { color: "#67C23A" } },
          { value: 89, name: "中负载(30-70%)", itemStyle: { color: "#E6A23C" } },
          { value: 38, name: "高负载(>70%)", itemStyle: { color: "#F56C6C" } },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };

  // 报警类型统计
  alarmTypeChartOptions.value = {
    tooltip: {
      trigger: "item",
    },
    series: [
      {
        type: "pie",
        radius: "60%",
        data: [
          { value: 156, name: "入侵检测", itemStyle: { color: "#F56C6C" } },
          { value: 89, name: "异常动作", itemStyle: { color: "#E6A23C" } },
          { value: 67, name: "火灾报警", itemStyle: { color: "#FF4500" } },
          { value: 45, name: "其他", itemStyle: { color: "#909399" } },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };
};

/**
 * 根据增长率计算对应的 CSS 类名
 */
const computeGrowthRateClass = (growthRate?: number): string => {
  if (!growthRate) {
    return "color-[--el-color-info]";
  }
  if (growthRate > 0) {
    return "color-[--el-color-danger]";
  } else if (growthRate < 0) {
    return "color-[--el-color-success]";
  } else {
    return "color-[--el-color-info]";
  }
};

/**
 * 获取活动颜色
 */
const getActivityColor = (type: string): string => {
  const colorMap: Record<string, string> = {
    报警: "#F56C6C",
    设备: "#4080FF",
    用户: "#67C23A",
    系统: "#E6A23C",
  };
  return colorMap[type] || "#909399";
};

/**
 * 获取活动标签类型
 */
const getActivityTagType = (type: string): string => {
  const typeMap: Record<string, string> = {
    报警: "danger",
    设备: "primary",
    用户: "success",
    系统: "warning",
  };
  return typeMap[type] || "info";
};

/**
 * 刷新设备状态
 */
const refreshDeviceStatus = () => {
  ElMessage.success("设备状态已刷新");
  initDeviceStatusCharts();
};

// 监听监控趋势日期范围的变化，重新获取趋势数据
watch(
  () => monitorTrendDateRange.value,
  (newVal) => {
    console.log("Monitor trend date range changed:", newVal);
    fetchMonitorTrendData();
  },
  { immediate: true }
);

// 组件挂载后加载系统统计数据
onMounted(() => {
  fetchSystemStatsData();
  initDeviceStatusCharts();
});
</script>

<style lang="scss" scoped>
.dashboard-container {
  position: relative;
  padding: 24px;

  .github-corner {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 1;
    border: 0;
  }

  .activity-item {
    padding: 16px;
    margin-bottom: 12px;
    background: var(--el-fill-color-lighter);
    border-radius: 8px;
    transition: all 0.2s;

    &.latest-item {
      background: var(--el-color-primary-light-9);
      border: 1px solid var(--el-color-primary-light-5);
    }

    &:hover {
      transform: translateX(5px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .activity-content {
      margin: 8px 0 12px 0;
      font-size: 13px;
      line-height: 1.5;
      color: var(--el-text-color-secondary);
      display: block;
    }

    .activity-location {
      font-size: 12px;
      color: var(--el-text-color-placeholder);
      display: flex;
      align-items: center;
      gap: 4px;
    }
  }

  .device-status-card {
    padding: 16px;
    background: var(--el-fill-color-lighter);
    border-radius: 8px;
    height: 100%;

    h4 {
      margin: 0 0 16px 0;
      color: var(--el-text-color-primary);
      font-size: 14px;
      font-weight: 500;
      text-align: center;
    }
  }

  // 自定义图标样式
  .i-svg\:home {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%2367C23A'%3E%3Cpath d='M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  .i-svg\:camera {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%234080FF'%3E%3Cpath d='M9 3L7.17 5H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V7c0-1.1-.9-2-2-2h-3.17L15 3H9zm3 15c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  .i-svg\:raspberry-pi {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23E6A23C'%3E%3Cpath d='M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  .i-svg\:capture {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23F56C6C'%3E%3Cpath d='M12 15.5A3.5 3.5 0 0 1 8.5 12A3.5 3.5 0 0 1 12 8.5a3.5 3.5 0 0 1 3.5 3.5 3.5 3.5 0 0 1-3.5 3.5m7.43-2.53c.04-.32.07-.64.07-.97 0-.33-.03-.66-.07-1l2.11-1.63c.19-.15.24-.42.12-.64l-2-3.46c-.12-.22-.39-.31-.61-.22l-2.49 1c-.52-.39-1.06-.73-1.69-.98l-.37-2.65A.506.506 0 0 0 14 2h-4c-.25 0-.46.18-.5.42l-.37 2.65c-.63.25-1.17.59-1.69.98l-2.49-1c-.22-.09-.49 0-.61.22l-2 3.46c-.13.22-.07.49.12.64L4.57 11c-.04.34-.07.67-.07 1 0 .33.03.65.07.97L2.46 14.6c-.19.15-.24.42-.12.64l2 3.46c.12.22.39.31.61.22l2.49-1c.52.39 1.06.73 1.69.98l.37 2.65c.04.24.25.42.5.42h4c.25 0 .46-.18.5-.42l.37-2.65c.63-.25 1.17-.59 1.69-.98l2.49 1c.22.09.49 0 .61-.22l2-3.46c.12-.22.07-.49-.12-.64l-2.11-1.63Z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  .i-svg\:visitor {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%2367C23A'%3E%3Cpath d='M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  .i-svg\:browser {
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%234080FF'%3E%3Cpath d='M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1 17.93c-3.94-.49-7-3.85-7-7.93 0-.62.08-1.21.21-1.79L9 15v1c0 1.1.9 2 2 2v1.93zm6.9-2.54c-.26-.81-1-1.39-1.9-1.39h-1v-3c0-.55-.45-1-1-1H8v-2h2c.55 0 1-.45 1-1V7h2c1.1 0 2-.9 2-2v-.41c2.93 1.19 5 4.06 5 7.41 0 2.08-.8 3.97-2.1 5.39z'/%3E%3C/svg%3E");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
  }

  // 响应式设计
  @media (max-width: 768px) {
    padding: 16px;

    .el-col {
      margin-bottom: 10px;
    }

    .device-status-card {
      padding: 12px;

      h4 {
        font-size: 12px;
        margin-bottom: 12px;
      }
    }
  }
}
</style>
