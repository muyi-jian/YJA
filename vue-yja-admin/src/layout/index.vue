<template>
  <div class="common_layout">
    <el-container>
      <el-aside width="200px"><Aside /></el-aside>
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

<script setup lang="ts"></script>

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
    background-color: #6565e4;
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
      background-color: #ccccff;
      border-top-right-radius: 8px;
      border-bottom: 1px solid #c9c6c6;
    }

    .el-main {
      --el-main-padding: 0 !important;
      flex: 1;
      overflow-y: auto;
      border-bottom-right-radius: 8px;
      background-color: #ffcccc;
    }
  }
}
</style>
