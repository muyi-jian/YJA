// 导入 Element Plus 中英文语言包
import { defineStore } from "pinia";
import { useStorage } from "@vueuse/core";

export const useAppStore = defineStore("app", () => {
  const device = useStorage("device", "desktop");
  const sidebarStatus = useStorage("sidebarStatus", "closed");
  const sidebar = reactive({
    opened: sidebarStatus.value !== "closed",
    withoutAnimation: false
  });
  // actions
  function toggleSidebar() {
    sidebar.opened = !sidebar.opened;
    if (sidebar.opened) {
      sidebarStatus.value = "opened";
    } else {
      sidebarStatus.value = "closed";
    }
  }
  return {
    sidebar,
    toggleSidebar,
    device
  };
});
