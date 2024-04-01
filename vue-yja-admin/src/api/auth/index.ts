import $api from "@/api/service/webRequest";
import { APIs } from "@/api/service/apiList";
import type { LoginData, LoginRes } from "./types";

/**
 * 登录
 */
export function loginApi(data: LoginData) {
  console.log("loginApi===============");
  const formData = new FormData();
  formData.append("username", data.username);
  formData.append("password", data.password);
  // formData.append("captchaKey", data.captchaKey || "");
  // formData.append("captchaCode", data.captchaCode || "");
  const config = {
    headers: {
      "Content-Type": "multipart/form-data"
      // 您可以在这里添加其他需要的头信息
    }
    // 这里还可以添加其他Axios配置，比如timeout, params等
  };

  return $api.post<LoginRes>(APIs.adminApi.login, data, config);
}

/**
 * 登出
 */
export function logoutApi() {
  return $api.delete<LoginRes>(APIs.adminApi.logout);
}
