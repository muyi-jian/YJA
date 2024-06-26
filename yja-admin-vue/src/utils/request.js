//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from "element-plus";
import router from "@/router/index.js";
//定义一个变量,记录公共的前缀  ,  baseURL
// const baseURL = 'http://localhost:8080';
const baseURL = '/api';
const instance = axios.create({baseURL})

import {useTokenStore} from "@/stores/token.js";
// 添加请求拦截器
instance.interceptors.request.use(
    config=>{
        const tokenStore = useTokenStore();
        // 添加token
        if (tokenStore.token){
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    err=>{
        return Promise.reject(err);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        // 判断后端响应状态
        if (result.data.code === 0){
            return result.data;
        }
        // 操作失败
        // alert(result.data.msg?result.data.msg:'服务异常')
        ElMessage.error(result.data.message?result.data.message:'服务异常')
        return Promise.reject(result.data);
    },
    err=>{
        if (err.response.status === 401){
            // 跳转到登录页面
            ElMessage.error('请先登录')
            router.push('/login');
        }else {
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;