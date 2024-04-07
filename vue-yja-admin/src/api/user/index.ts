import $api from "@/api/service/webRequest";
import { APIs } from "@/api/service/apiList";
import type { UserInfo } from "./types";

/**
 * 获取登录用户信息
 */
export function getUserInfoApi() {
  return $api.get<UserInfo>(APIs.userApi.getUserInfo);
}
