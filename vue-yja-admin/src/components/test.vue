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

import { useCounterStore } from '@/store/modules/counter';
const counterStore= useCounterStore();
import { onMounted } from 'vue'

import { test } from '@/api'
onMounted(() => {
  test().then(res => {
    console.log('res=>', res)
  })
})

</script>

<style scoped lang="scss">
.item{
  background: $bg-color;
}
</style>
