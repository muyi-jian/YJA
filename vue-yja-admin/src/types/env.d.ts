// src/types/env.d.ts
interface ImportMetaEnv {
  /**
   * 环境标识
   */
  NODE_ENV: string;
  /**
   * 应用标题
   */
  VITE_APP_TITLE: string;
  /**
   * 应用端口
   */
  VITE_APP_PORT: number;
  /**
   * API基础路径(反向代理)
   */
  VITE_APP_BASE_API: string;
  /**
   * API基础路径(文件上传下载)
   */
  VITE_APP_BASE_FILE_API: string;
  /**
   * 后端服务地址
   */
  VITE_APP_SERVICE_API: string;

}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
