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
    <div><AsideMenu :menu-list="permissionStore.routes" base-path="" /></div>
  </div>
</template>

<script lang="ts" setup>
import { useAppStore } from "@/store/modules/app.ts";

const appStore = useAppStore();
import { useRoute } from "vue-router";
import { usePermissionStore } from "@/store/modules/permission";
const permissionStore = usePermissionStore();
const currentRoute = useRoute();
console.log(currentRoute.path);
console.log("333333333333333333");
console.log(permissionStore.routes);
console.log("333333333333333333");

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
