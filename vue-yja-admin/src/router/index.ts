import { createRouter, createWebHistory } from "vue-router";

export const constantRoutes = [
  // 默认路由:进入项目 默认进入 /index 页面
  {
    path: "/",
    component: () => import("@/views/home/index.vue"),
    meta: {
      keepalive: true,
      title: "主页"
    },
    name: "Home",
    // hideMenu: true,//不展示在侧边栏
    children: []
    // redirect: '/index'
  },
  {
    path: "/test",
    name: "test page",
    //使用import可以路由懒加载，如果不使用，太多组件一起加载会造成白屏
    component: () => import("@/views/test/test.vue")
  },
  {
    path: "/login",
    name: "Login",
    meta: {
      title: "登录"
    },
    component: () => import("@/views/login/index.vue"),
    hidden: true
  },
  {
    path: "/:pathMatch(.*)*", // 此写法解决动态路由页面刷新的 warning 警告
    component: () => import("@/views/error-page/404.vue"),
    hidden: true
  }
];
// 路由
const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes
});
// 导出
export default router;
