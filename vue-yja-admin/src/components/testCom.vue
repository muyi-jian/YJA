<template>
  <div>
    <el-row class="mb-4">
      <el-button>Default</el-button>
      <el-button type="success"><i-ep-SuccessFilled />Success</el-button>
      <el-button type="info"><i-ep-InfoFilled />Info</el-button>
      <el-button type="warning"><i-ep-WarningFilled />Warning</el-button>
      <el-button type="danger"><i-ep-WarnTriangleFilled />Danger</el-button>
    </el-row>

    <div class="item">你好</div>

    <!-- <div style="width:100px;height:100px" :style="{ 'background-color': variables.bgColor }" />-->
    <div>{{ counterStore.count }}</div>
    <el-button type="primary" @click="counterStore.increment">count++</el-button>
  </div>
</template>

<script lang="ts" setup>
import variables from "@/styles/variables.module.scss";
console.log(variables.bgColor);

import { useCounterStore } from "@/store/modules/counter";
const counterStore = useCounterStore();

import $api from "@/api/service/webRequest";
import AxiosRequestError from "@/api/service/error";
import { APIs } from "@/api/service/apiList";
// $api.getUniversity().then(res=>{
//   console.log(res)
// }).catch((err:AxiosRequestError)=>{
//   console.dir(err)
// })
let tdata = {
  name: "yj",
  age: 18
};
$api
  .post(APIs.webApi.getUniversity, tdata)
  .then((res) => {
    console.log(res);
  })
  .catch((err: AxiosRequestError) => {
    console.dir(err);
  });
</script>

<style scoped lang="scss">
.item {
  background: $bg-color;
}
</style>
