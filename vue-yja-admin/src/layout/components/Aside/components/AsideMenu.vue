<template>
  <el-menu
    active-text-color="#ffd04b"
    :collapse="!appStore.sidebar.opened"
    :background-color="variables['menu-background']"
    class="el-menu-vertical-demo"
    :default-active="currentPath"
    text-color="#fff"
    @open="handleOpen"
    @close="handleClose"
  >
    <AsideMenuItem
      v-for="route in menuList"
      :key="route.path"
      :item="route"
      :base-path="resolvePath(route.path)"
      :is-collapse="!appStore.sidebar.opened"
    />
  </el-menu>
</template>

<script lang="ts" setup>
import variables from "@/assets/styles/common/variables.module.scss";
import { computed } from "vue";
import { useRoute } from "vue-router";
import { isExternal } from "@/utils/index";
import path from "path-browserify";
import { useAppStore } from "@/store/modules/app";
const appStore = useAppStore();
const currentRoute = useRoute();

// 遍历路由数组

const currentPath = computed(() => currentRoute.path);

const props = defineProps({
  menuList: {
    required: true,
    default: () => {
      return [];
    },
    type: Array<any>
  },
  basePath: {
    type: String,
    required: true
  }
});
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};

/**
 * 解析路径
 *
 * @param routePath 路由路径 /user
 */
function resolvePath(routePath: string) {
  if (isExternal(routePath)) {
    return routePath;
  }
  if (isExternal(props.basePath)) {
    return props.basePath;
  }

  // 完整绝对路径 = 父级路径(/system) + 路由路径(/user)
  const fullPath = path.resolve(props.basePath, routePath);
  return fullPath;
}
</script>

<style lang="scss" scoped></style>
