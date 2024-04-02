<template>
  <div class="common_layout">
    <el-container>
      <el-aside :class="classObj"><Aside /></el-aside>
      <div class="content">
        <div class="header-wrapper">
          <el-header><Header /></el-header>
        </div>
        <el-main><AppMain /></el-main>
        <!-- <el-footer><Footer /></el-footer> -->
      </div>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { useAppStore } from "@/store/modules/app.ts";
import { useSettingsStore } from "@/store/modules/settings.ts";

const appStore = useAppStore();
const settingsStore = useSettingsStore();

const layout = computed(() => settingsStore.layout); // 布局模式 left top mix

const classObj = computed(() => ({
  hideSidebar: !appStore.sidebar.opened,
  openSidebar: appStore.sidebar.opened,
  mobile: appStore.device === "mobile",
  "layout-left": layout.value === "left",
  "layout-top": layout.value === "top",
  "layout-mix": layout.value === "mix"
}));
</script>

<style lang="scss" scoped>
.common_layout {
  height: 100vh;
  box-sizing: border-box;
  padding: 10px 5px;
}

.el-container {
  height: 100%;
  display: flex;
  flex-direction: row;

  .el-aside {
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
    background-color: #304156;
    width: $sidebar-width;
    overflow: hidden;
    transition: width 0.28s;
  }
  .hideSidebar {
    // left: $sidebar-width-collapsed;
    width: $sidebar-width-collapsed;
  }
  .content {
    flex: 1;
    display: flex;
    flex-direction: column;
    height: 100%;

    .header-wrapper {
      position: sticky;
      top: 0;
      z-index: 1; /* 确保 header 在其他元素之上 */
      // background-color: #ccccff;
      border-top-right-radius: 8px;
      // border-bottom: 1px solid #c9c6c6;
    }
    .el-header {
      --el-header-padding: 0 !important;
      --el-header-height: 0 !important;
    }

    .el-main {
      --el-main-padding: 0 !important;
      flex: 1;
      overflow-y: auto;
      border-bottom-right-radius: 8px;
      // background-color: #ffcccc;
    }
  }
}
</style>
