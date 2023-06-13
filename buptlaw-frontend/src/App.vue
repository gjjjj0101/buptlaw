<script setup lang="ts">
import NavigatorBar from "./components/navigator-bar.vue";

</script>

<template>
  <el-container>
    <el-header style="padding: 0">
      <navigator-bar/>
    </el-header>
    <el-main style="height: 2000px">Main</el-main>
<!--    <el-footer style="background: red">Footer</el-footer>-->
  </el-container>
</template>

<style>
* {
  margin: 0;
}

:root {
  font-family: "PingFang SC", "Microsoft Yahei", "Helvetica Neue", Helvetica, "Segoe UI", Arial, sans-serif;
  overflow-x: hidden;

  --blue: #409eff;

  --text-title-color: #333333;
  --text-content-color: #444444;
  --shadow-color: #eeeeee;
}

</style>
