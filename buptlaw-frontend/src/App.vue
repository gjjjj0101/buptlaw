<script setup lang="ts">
import NavigatorBar from "./components/navigator-bar.vue";

</script>

<template>
  <el-container>
    <el-header style="padding: 0; height: 84px; z-index: 9">
      <navigator-bar/>
    </el-header>
    <el-main style="padding: 0">
      <router-view/>
    </el-main>
<!--    <el-footer style="background: red">Footer</el-footer>-->
  </el-container>
</template>

<style>
* {
  margin: 0;
  padding: 0;
}

:root {
  font-family: "PingFang SC", "Microsoft Yahei", "Helvetica Neue", Helvetica, "Segoe UI", Arial, sans-serif;
  overflow-x: hidden;

  --theme-color: #409eff;

  --text-color: #333333;
  --text-gray-color: #444444;
  --shadow-color: #eeeeee;
}

.card {
  background: #ffffff;
  border-radius: 8px;
  box-shadow: var(--shadow-color) 0 8px 24px;
  overflow: hidden;
  display: inline-block;
  flex-direction: column;
  padding: 24px;
}
</style>
