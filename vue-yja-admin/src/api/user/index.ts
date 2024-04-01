import $api from "@/api/service/webRequest";
import { APIs } from "@/api/service/apiList";
import type { UserInfoRes } from "./types";

/**
 * 获取登录用户信息
 */
export function getUserInfo() {
  return $api.get<UserInfoRes>(APIs.adminApi.getUserInfo);
}
