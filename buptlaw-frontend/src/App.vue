<script setup lang="ts">

</script>

<template>
  <router-view/>
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
  --theme-active-color: #66b1ff;

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
  box-sizing: border-box;
}
</style>
