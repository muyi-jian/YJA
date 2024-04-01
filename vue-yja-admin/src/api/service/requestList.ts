import { AxiosRequestConfig, AxiosResponse } from "axios";
import createRequestInstance from "./request";
import { APIs } from "./apiList";

class API {
  request!: ReturnType<typeof createRequestInstance>;

  get!: <T = any, R = AxiosResponse<T>>(url: string, config?: AxiosRequestConfig) => Promise<R>;

  delete!: <T = any, R = AxiosResponse<T>>(url: string, config?: AxiosRequestConfig) => Promise<R>;

  head!: <T = any, R = AxiosResponse<T>>(url: string, config?: AxiosRequestConfig) => Promise<R>;

  options!: <T = any, R = AxiosResponse<T>>(url: string, config?: AxiosRequestConfig) => Promise<R>;

  post!: <T = any, R = AxiosResponse<T>>(url: string, data?: any, config?: AxiosRequestConfig) => Promise<R>;

  put!: <T = any, R = AxiosResponse<T>>(url: string, data?: any, config?: AxiosRequestConfig) => Promise<R>;

  patch!: <T = any, R = AxiosResponse<T>>(url: string, data?: any, config?: AxiosRequestConfig) => Promise<R>;

  constructor(options: { url: string }) {
    console.log("usrl====" + options.url);
    const request = createRequestInstance(options.url);
    this.request = request;
    this.post = request.post.bind(this);
    this.put = request.put.bind(this);
    this.get = request.get.bind(this);
    this.delete = request.delete.bind(this);
    this.head = request.head.bind(this);
    this.options = request.options.bind(this);
    this.patch = request.patch.bind(this);
  }

  async getUniversity() {
    const res = await this.post(APIs.webApi.getUniversity);
    return res;
  }
}

export default API;
