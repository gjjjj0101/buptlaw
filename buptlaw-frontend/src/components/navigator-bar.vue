<script setup>

</script>

<template>
  <div id="navigator-bar">
    <font-awesome-icon icon="landmark" id="navigator-bar-logo"/>
    <span id="navigator-bar-text">北邮某某律所</span>
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

  padding: 4px 120px;
  font-size: 24px;

  box-shadow: var(--shadow-color) 0 8px 20px;
}

#navigator-bar-logo {
  color: var(--blue);
  font-size: 36px;
}

#navigator-bar-text {
  margin-left: 12px;
  color: var(--text-title-color);
}
</style>
