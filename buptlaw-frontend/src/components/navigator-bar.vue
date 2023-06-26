<script setup>
import {router} from "../router/index";

const toToolsPage = () => {
  router.push({name: 'tools'})
}
</script>

<template>
  <div id="navigator-bar">
    <div id="navigator-bar-logo-box" @click="toToolsPage">
      <font-awesome-icon icon="gavel" id="navigator-bar-logo"/>
      <span id="navigator-bar-text">某某律所</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "navigator-bar"
}
</script>

<style scoped>
#navigator-bar {
  height: 100%;

  display: flex;
  align-items: center;

  padding: 0 0 0 240px;
  border-bottom: 1px solid rgb(220, 223, 230);
}

#navigator-bar-logo-box {
  cursor: pointer;
}

#navigator-bar-logo {
  color: var(--theme-color);
  font-size: 36px;
}

#navigator-bar-text {
  font-size: 28px;
  font-weight: bold;
  margin-left: 16px;
  color: var(--theme-color);
}
</style>
