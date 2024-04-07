// 平台所有的api
export const APIs = {
  // 后台api
  adminApi: {
    login: "/api/admin/login",
    logout: "/api/admin/logout",
    getRoutes: "/api/admin/getRoutes",
    getMenus: "/api/admin/getMenus",
    getPermissions: "/api/admin/getPermissions",
    getRoles: "/api/admin/getRoles",
    getUsers: "/api/admin/getUsers",
    getUser: "/api/admin/getUser",
    addUser: "/api/admin/addUser",
    editUser: "/api/admin/editUser"
  },
  userApi: {
    getUserInfo: "/api/users/me"
  },
  // 前台Api
  webApi: {
    login: "/api/web/login",
    getUniversity: "/getUniversity"
  },
  menuApi: {
    getListRoutes: "/api/menus/routes"
  }
};
