//  src/utils/request.ts
import axios, { AxiosError, AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';

// 创建 axios 实例
const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 10000,
  withCredentials: true //允许后台的cookie传递到前端
  // headers: {
  //   'Content-Type': 'application/json;charset=UTF-8'
  // }
})



// 添加请求拦截器
instance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  return config;
}, (error: AxiosError) => {
  return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use((response: AxiosResponse) => {
  const {code, data, message} = response.data;
  if (code === 200) {
    return data;
  }
  ElMessage.error(message)
  return Promise.reject(new Error(message))
}, (error: AxiosError) => {
  let message = ""
  const status = error.response?.status;
  if (status == "ER00001") {
    ElMessageBox.confirm('当前页面已失效，请重新登录', '提示', {
      confirmButtonText: '确定',
      type: 'warning'
    }).then(() => {
      localStorage.clear(); // @vueuse/core 自动导入
      window.location.href = '/';
    });
  }else{
    switch (status) {
      case 401:
        message = "token失效，请重新登录"
        // 这里写退出登录逻辑
        break
      case 404:
        message = "请求地址错误"
        break
      case 500:
        message = "服务器繁忙"
        break
      default:
        message = "网络链接故障"
    }
    ElMessage.error(message)
  }
  return Promise.reject(error);
});


// 导出 axios 实例
export default instance;

