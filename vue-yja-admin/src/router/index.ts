import { createRouter, createWebHistory } from "vue-router";
export const Layout = () => import("@/layout/index.vue");

export const constantRoutes = [
  // 默认路由:进入项目 默认进入 /index 页面
  // {
  //   path: "/",
  //   component: () => import("@/views/home/index.vue"),
  //   meta: {
  //     keepalive: true,
  //     title: "主页"
  //   },
  //   name: "Home",
  //   // hideMenu: true,//不展示在侧边栏
  //   children: []
  //   // redirect: '/index'
  // },
  {
    path: "/redirect",
    component: Layout,
    meta: { hidden: true },
    children: [
      {
        path: "/redirect/:path(.*)",
        component: () => import("@/views/redirect/index.vue")
      }
    ]
  },

  {
    path: "/login",
    component: () => import("@/views/login/index.vue"),
    meta: { hidden: true }
  },

  {
    path: "/",
    name: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index.vue"),
        name: "Dashboard", // 用于 keep-alive, 必须与SFC自动推导或者显示声明的组件name一致
        // https://cn.vuejs.org/guide/built-ins/keep-alive.html#include-exclude
        meta: {
          title: "首页",
          icon: "homepage",
          affix: true,
          keepAlive: true,
          alwaysShow: false
        }
      },
      {
        path: "401",
        component: () => import("@/views/error-page/401.vue"),
        meta: { hidden: true }
      },
      {
        path: "404",
        component: () => import("@/views/error-page/404.vue"),
        meta: { hidden: true }
      }
    ]
  },
  {
    path: "/test",
    name: "test page",
    //使用import可以路由懒加载，如果不使用，太多组件一起加载会造成白屏
    component: () => import("@/views/test/test.vue"),
    meta: { hidden: true }
  }
  // {
  //   path: "/login",
  //   name: "Login",
  //   meta: {
  //     title: "登录"
  //   },
  //   component: () => import("@/views/login/index.vue"),
  //   hidden: true
  // },
  // {
  //   path: "/:pathMatch(.*)*", // 此写法解决动态路由页面刷新的 warning 警告
  //   component: () => import("@/views/error-page/404.vue"),
  //   hidden: true
  // }
];
// 路由
const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes
});

/**
 * 重置路由
 */
export function resetRouter() {
  router.replace({ path: "/login" });
}

// 导出
export default router;
