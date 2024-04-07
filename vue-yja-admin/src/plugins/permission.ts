import router from "@/router";
import { useUserStore } from "@/store/modules/user";
import { usePermissionStore } from "@/store/modules/permission";

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
        const userStore = useUserStore();
        const hasRoles = userStore.user.roles && userStore.user.roles.length > 0;
        if (hasRoles) {
          // 未匹配到任何路由，跳转404
          if (to.matched.length === 0) {
            from.name ? next({ name: from.name }) : next("/404");
          } else {
            next();
          }
        } else {
          const permissionStore = usePermissionStore();
          try {
            // 获取角色信息
            const { roles } = await userStore.getUserInfo();
            // 获取用户权限
            const accessRoutes = await permissionStore.generateRoutes(roles);
            // 动态添加路由
            accessRoutes.forEach((route) => {
              router.addRoute(route);
            });
            next({ ...to, replace: true });
          } catch (error) {
            // 移除 token 并跳转登录页
            await userStore.resetToken();
            next(`/login?redirect=${to.path}`);
            // NProgress.done();
          }
        }
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
