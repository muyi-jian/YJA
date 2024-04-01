// 平台所有的api
export const APIs = {
  // 后台api
  adminApi: {
    login: "/admin/login",
    logout: "/admin/logout",
    getUserInfo: "/admin/getUserInfo",
    getRoutes: "/admin/getRoutes",
    getMenus: "/admin/getMenus",
    getPermissions: "/admin/getPermissions",
    getRoles: "/admin/getRoles",
    getUsers: "/admin/getUsers",
    getUser: "/admin/getUser",
    addUser: "/admin/addUser",
    editUser: "/admin/editUser"
  },
  // 前台Api
  webApi: {
    login: "/web/login",
    getUniversity: "/getUniversity"
  }
};
