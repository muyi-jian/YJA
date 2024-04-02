<template>
  <div>
    <div class="logo-container">
      <transition enter-active-class="animate__animated animate__fadeInLeft">
        <router-link v-if="!appStore.sidebar.opened" class="wh-full flex-center" to="/">
          <img v-if="sidebarLogo" :src="logo" class="logo-image" />
        </router-link>

        <router-link v-else class="wh-full flex-center" to="/">
          <img v-if="sidebarLogo" :src="logo" class="logo-image" />
          <span class="logo-title"> {{ title }}</span>
        </router-link>
      </transition>
    </div>
    <div>
      <el-menu
        active-text-color="#ffd04b"
        :collapse="!appStore.sidebar.opened"
        :background-color="variables['menu-background']"
        class="el-menu-vertical-demo"
        default-active="2"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <location />
            </el-icon>
            <span>Navigator One</span>
          </template>
          <el-menu-item-group title="Group One">
            <el-menu-item index="1-1">item one</el-menu-item>
            <el-menu-item index="1-2">item two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="1-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon>
            <icon-menu />
          </el-icon>
          <span>Navigator Two</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon>
            <document />
          </el-icon>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon>
            <setting />
          </el-icon>
          <span>Navigator Four</span>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useAppStore } from "@/store/modules/app.ts";
const appStore = useAppStore();
import variables from "@/assets/styles/common/variables.module.scss";
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const logo = ref(new URL(`../../../assets/logo.png`, import.meta.url).href);

const sidebarLogo = ref(true);
const title = ref("YJA-admin");
</script>
<style lang="scss" scoped>
.logo-container {
  width: 100%;
  height: 50px;
  background-color: #2d3748;

  .logo-image {
    width: 20px;
    height: 20px;
  }

  .logo-title {
    flex-shrink: 0; /* 防止容器在空间不足时缩小 */
    margin-left: 10px;
    font-size: 14px;
    font-weight: bold;
    color: white;
  }
}
.layout-top,
.layout-mix {
  .logo-container {
    width: 210px;
  }

  &.hideSidebar {
    .logo-container {
      width: 54px;
    }
  }
}
.el-menu {
  border-right: none;
  height: 100%;
}
.hideSidebar {
  .el-menu {
    width: $sidebar-width-collapsed !important;
    .el-sub-menu {
      overflow: hidden;

      & > .el-sub-menu__title {
        padding: 0 !important;

        .sub-el-icon {
          margin-left: 19px;
        }

        .el-sub-menu__icon-arrow {
          display: none;
        }
      }
    }
  }
}
</style>
