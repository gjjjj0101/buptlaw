<script setup lang="ts">
import { ElNotification } from "element-plus";

const notOpenNotification = () => {
  ElNotification({
    title: '功能未开放',
    type: 'error',
  })
}

interface ToolsItem {
  icon: string,           // 图标名称
  name: string,           // 工具名称

  isLink?: boolean,       // 是否为链接
  pathName?: string,      // 路由名称

  background?: string,    // 背景色
  fallback?: () => void,  // 点击时的回调
}

const lawToolsList: ToolsItem[] = [
  {
    icon: 'book-bookmark',
    name: '法条查询',
    isLink: true,
    pathName: 'law',
    background: 'linear-gradient(150deg, #F37335, #FDC830)',
  },
  {
    icon: 'file-invoice',
    name: '刑审智库',
    background: 'linear-gradient(150deg, #2B32B2, #1488CC)',
    fallback: notOpenNotification,
  },
  {
    icon: 'file-contract',
    name: '元典智库',
    background: 'linear-gradient(150deg, #4e54c8, #8f94fb)',
    fallback: notOpenNotification,
  },
  {
    icon: 'comments',
    name: '法治宣传',
    background: 'linear-gradient(150deg, #2193b0, #6dd5ed)',
  }
]
</script>

<template>
  <div id="main">
    <div class="card" style="width: 420px; ">
      <div class="card-title">法律法规相关</div>

      <el-row class="tools-box">
        <el-col :span="6" v-for="i in lawToolsList" style="display: flex; justify-content: center">
          <div class="tools-item" @click="i.fallback">
            <div class="tools-item-icon" :style="'background: ' + i.background">
              <font-awesome-icon :icon="i.icon"/>
            </div>
            <div class="tools-item-text">{{ i.name }}</div>

            <router-link v-if="i.isLink" :to="{ name: i.pathName }" class="tools-item-link"/>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script lang="ts">
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
}

.tools-box {
  margin-top: 12px;
  width: 100%;
}

.tools-item {
  display: inline-block;
  width: 64px;
  margin: 12px 0;
  text-align: center;
  transition: all 0.2s;
  color: var(--text-color);
  user-select: none;
  cursor: pointer;
  position: relative;
}

.tools-item:hover .tools-item-text {
  color: var(--theme-color);
}

.tools-item-icon {
  width: 64px;
  height: 64px;
  border-radius: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #8e9eab;
  color: rgba(255, 255, 255, 0.9);
  font-size: 32px;
}

.tools-item-text {
  margin-top: 8px;
  font-size: 14px;
}

.tools-item-link {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
