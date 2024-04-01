<template>
  <div class="login-container">
    <!--设置卡片头部-->
    <!--通过header设置插槽，插槽最大的特点就是可以将html以及文本都放进去-->
    <!--插槽其实就是预留了位置-->
    <el-card class="login-card">
      <template #header>
        <div class="login-card-header">
          <span>用户登录</span>
        </div>
      </template>
      <!--卡片的body  表单里面的数据全部都是对象里面的数据-->
      <!-- ref设置后，在script中能使用this.$refs拿到对应的对象，用于验证和reset表单数据 -->
      <el-form :model="loginData" :rules="loginDataRules" ref="loginFormRef">
        <!--这里的prop要跟rules里面的规则名对其用来校验规则-->
        <el-form-item prop="username">
          <!--refix-icon="UserFilled"图标做了全局引入，这里就不需要引入了-->
          <!--v-model.trim 其实双向绑定了loginData.username  trim其实就是去掉空格-->
          <!--placeholder 默认文字提示   placeholde 清除按钮-->
          <el-input
            prefix-icon="UserFilled"
            v-model.trim="loginData.username"
            maxlength="32"
            placeholder="请输入账号"
            clearable
          >
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            type="password"
            prefix-icon="Lock"
            v-model.trim="loginData.password"
            maxlength="16"
            placeholder="请输入密码"
            clearable
          >
          </el-input>
        </el-form-item>
        <!--这里面不需要加校验 因为只是一个按钮-->
        <!--style="width: 100%;border-radius: 5px;" 这里设置的样式 圆角-->
        <el-form-item>
          <el-button
            type="primary"
            style="width: 100%; border-radius: 5px"
            :loading="loginLoading"
            @click="handleLogin()"
            >登入
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/modules/user";
import { LoginData } from "@/api/auth/types";
import type { FormInstance } from "element-plus";
import router from "@/router";
import { ref, computed } from "vue";
// LocationQuery: 代表路由查询参数的类型。
// LocationQueryValue: 代表路由查询参数值的类型。
// useRoute: 是一个 Vue Composition API 的函数，用于获取当前路由的信息。
import { LocationQuery, LocationQueryValue, useRoute } from "vue-router";

// Stores
const userStore = useUserStore();

const loginFormRef = ref<FormInstance | null>(null); // 登录表单ref
const loginLoading = ref(false);
const loginData = ref<LoginData>({
  username: "",
  password: ""
});

const loginDataRules = computed(() => {
  return {
    //是不是必填项，校验失败之后的提示。触发方式，change和blur，change是发生数据变化就会触发校验
    //blur是失去焦点触发校验
    //支持复杂的校验和自定义逻辑的校验，一般情况下这种就够用了。真正复杂的校验逻辑在后端
    //直接敲不触发校验规则，校验只有change的时候触发，就是发生变化的时候触发。
    username: [{ required: true, message: "请填写用户名", trigger: "change" }],
    password: [{ required: true, message: "请填写密码", trigger: "change" }]
  };
});

// const password = reactive([{}]);
const route = useRoute();

function handleLogin() {
  if (loginFormRef.value) {
    loginFormRef.value.validate((valid: boolean) => {
      if (valid) {
        console.log(loginData.value);
        userStore
          .login(loginData.value)
          .then(() => {
            console.log("登录成功");
            // 获取的当前路由对象
            const query: LocationQuery = route.query;
            // 从 query 对象中获取 redirect 的值。如果 redirect 存在，则使用它的值；如果不存在或值为假值（如 null、undefined、0、false 等），则使用默认的 /。
            const redirect = (query.redirect as LocationQueryValue) ?? "/";
            //使用 reduce 方法来遍历 query 对象中的所有键，并构建一个新的对象 otherQueryParams。这个新对象包含除了 redirect 以外的所有查询参数和它们的值
            const otherQueryParams = Object.keys(query).reduce((acc: any, cur: string) => {
              if (cur !== "redirect") {
                acc[cur] = query[cur];
              }
              return acc;
            }, {});

            router.push({ path: redirect, query: otherQueryParams });
          })
          .catch(() => {
            console.log("登录失败");
          })
          .finally(() => {
            console.log("finally");
          });
      } else {
        console.log("error submit!!");
        return false;
      }
    });
  }
}
</script>

<style lang="scss" scoped>
/*style中要加scoped,scoped用于各个页面之间的css属性的隔离，避免互相污染全局生效*/
.login-container {
  position: absolute; /*图片平铺开来*/
  width: 100%; /*最外层已经是body，有一层vh和vw了，其实定义完最外层就可以了，login会继承*/
  height: 100%;
  background-image: url(../../assets/images/login-bg.jpg);
  background-size: 100%;
}

.login-card {
  position: absolute;
  left: 35%;
  top: 35%;
  width: 450px;
  border-radius: 10px;
}

.login-card-header {
  //display: flex;
  //justify-content: center;
  //align-items: center;
  text-align: center;
}
</style>
