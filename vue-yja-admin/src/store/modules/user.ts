import { loginApi, logoutApi } from "@/api/auth";
import { LoginData } from "@/api/auth/types";
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", () => {
  function login(loginData: LoginData) {
    return new Promise<void>((resolve, reject) => {
      loginApi(loginData)
        .then((resp) => {
          const { tokenType, accessToken } = resp.data.data;
          console.log(tokenType, accessToken);
          localStorage.setItem("token", tokenType + " " + accessToken); // Bearer eyJhbGciOiJIUzI1NiJ9.xxx.xxx
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  }
  function logout() {
    return new Promise<void>((resolve, reject) => {
      logoutApi()
        .then(() => {
          localStorage.removeItem("token");
          location.reload(); // 清空路由
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  }
  return {
    login,
    logout
  };
});
