<script setup lang="ts">
import NavigatorBar from "../components/navigator-bar.vue";
import AiChat       from "../components/ai-chat.vue";

</script>

<template>
  <el-container>
    <el-header style="padding: 0; height: 84px; z-index: 9">
      <navigator-bar/>
    </el-header>
    <el-main style="padding: 0; min-height: calc(100vh - 84px); background: #F9F9F9">
      <router-view/>
      <ai-chat/>
    </el-main>
    <!--    <el-footer style="background: red">Footer</el-footer>-->
  </el-container>
</template>

<script lang="ts">
export default {
  name: "main-page"
}
</script>

<style scoped>

</style>
