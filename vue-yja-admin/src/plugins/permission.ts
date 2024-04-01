import router from "@/router";

export function setupPermission() {
  // 白名单路由
  const whiteList = ["/login"];
  router.beforeEach(async (to, from, next) => {
    // 获取token
    // localStorage.setItem("token", "11111111111111111111111");
    const hasToken = localStorage.getItem("token");
    if (hasToken) {
      if (to.path === "/login") {
        console.log("已登录...");
        // 如果已登录，跳转首页
        next({ path: "/" });
        // NProgress.done();
      } else {
        console.log("进行登录处理...");
        next();
      }
    } else {
      // 未登录可以访问白名单页面
      if (whiteList.indexOf(to.path) !== -1) {
        next();
      } else {
        next(`/login?redirect=${to.path}`);
        // NProgress.done();
      }
    }
  });
  router.afterEach(() => {
    // NProgress.done();
  });
}
