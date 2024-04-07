import $api from "@/api/service/webRequest";
import { APIs } from "@/api/service/apiList";

/**
 * 获取路由列表
 */
export function listRoutes() {
  return $api.get(APIs.menuApi.getListRoutes);
}
