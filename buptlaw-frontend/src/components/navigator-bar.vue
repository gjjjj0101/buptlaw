<script setup>

</script>

<template>
  <div id="navigator-bar">
    <font-awesome-icon icon="gavel" id="navigator-bar-logo"/>
    <span id="navigator-bar-text">某某律所</span>
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
