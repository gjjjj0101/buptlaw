<script setup lang="ts">
import { router }         from "../router/index";
import { onMounted, ref } from "vue";

const routerDirect = (name: string) => {
  router.push({name: name})
}

const menuDefaultActive = ref<string>('1')

onMounted(() => {
  // 获取当前路由来决定 menuDefaultActive 的值
  const currentRoute = router.currentRoute.value.name
  if (currentRoute === 'case-analysis') {
    menuDefaultActive.value = '1'
  } else if (currentRoute === 'case-chat') {
    menuDefaultActive.value = '2'
  }
})
</script>

<template>
  <el-container>
    <el-aside width="240px">
      <el-menu :default-active="menuDefaultActive" style="min-height: calc(100vh - 84px); height: 100%">
        <el-menu-item index="1" @click="routerDirect('case-analysis')" style="font-size: 16px">
          <font-awesome-icon icon="magnifying-glass-chart"/>
          <span style="margin-left: 8px">案情分析列表</span>
        </el-menu-item>
        <el-menu-item index="2" @click="routerDirect('case-chat')" style="font-size: 16px">
          <font-awesome-icon icon="comment" />
          <span style="margin-left: 8px">AI问答</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script lang="ts">
export default {
  name: "case-page"
}
</script>

<style scoped>

</style>
