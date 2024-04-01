import { createApp } from "vue";
import App from "./App.vue";
import "@/assets/style/common.scss";

const app = createApp(App);
// 导入pinia
import { createPinia } from "pinia";
app.use(createPinia());

// 导入路由
import router from "@/router/index";
app.use(router);

import { setupPermission } from "@/plugins/permission";
// 注册动态路由
setupPermission();

app.mount("#app");
