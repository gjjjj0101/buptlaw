<script setup>
import {ElNotification} from "element-plus";

const notOpenNotification = () => {
  ElNotification({
    title: '功能未开放',
    type: 'error',
  })
}
</script>

<template>
  <div id="main">
    <div class="card" style="height: 200px; width: 480px;">
      <div class="card-title">法律法规相关</div>

      <div style="display: flex; justify-content: center">
        <div class="tools-box">
          <div class="tools-item">
            <router-link :to="{ name: 'law' }" style="text-decoration: none; color: var(--text-color)">
              <div class="tools-item-icon">
                <font-awesome-icon icon="book"/>
              </div>
              <div class="tools-item-text">法条查询</div>
            </router-link>
          </div>

          <div class="tools-item" v-for="i in 3" @click="notOpenNotification">
            <div class="tools-item-icon" style="background: gray">
              <font-awesome-icon icon="xmark"/>
            </div>
            <div class="tools-item-text">未开放</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "main-page"
}
</script>

<style scoped>
#main {
  background: #eeeeee;
  height: 2000px;
  padding: 48px 240px;
}

.card-title {
  font-size: 20px;
  font-weight: bold;
  color: var(--text-color);
  margin: 24px 0 0 24px;
}

.tools-box {
  margin-top: 24px;
  padding: 0 24px;
}

.tools-item {
  display: inline-block;
  width: 64px;
  margin: 0 18px;
  text-align: center;
  transition: all 0.2s;
  color: var(--text-color);
  user-select: none;
  cursor: pointer;
}

.tools-item:hover .tools-item-text {
  color: var(--blue);
}

.tools-item-icon {
  width: 64px;
  height: 64px;
  border-radius: 12px;
  background: linear-gradient(150deg, #283048, #859398);

  display: flex;
  justify-content: center;
  align-items: center;

  color: rgba(255, 255, 255, 0.9);
  font-size: 28px;
}

.tools-item-text {
  margin-top: 8px;
  font-size: 14px;
}
</style>
