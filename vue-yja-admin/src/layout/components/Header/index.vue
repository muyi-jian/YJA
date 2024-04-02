<template>
  <!-- 顶部导航栏 -->
  <div class="navbar-container">
    <!-- 导航栏左侧 -->
    <NavbarLeft />
    <!-- 导航栏右侧 -->
    <NavbarRight />
  </div>
</template>

<style lang="scss" scoped>
.navbar-container {
  @apply flex-x-between;

  height: $navbar-height;
  background: var(--el-bg-color);
  border-top: 1px solid #e4e7ed; /* 设置上边框为1px实线 */
  border-bottom: 1px solid #e4e7ed; /* 设置下边框为1px实线 */
}
</style>
