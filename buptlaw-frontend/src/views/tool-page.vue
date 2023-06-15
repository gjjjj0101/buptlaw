<script setup lang="ts">
import { ElNotification } from "element-plus";

const notOpenNotification = () => {
  ElNotification({
    title: '功能未开放',
    type: 'error',
  })
}

interface ToolItem {
  icon: string,           // 图标名称
  name: string,           // 工具名称

  isLink?: boolean,       // 是否为链接
  pathName?: string,      // 路由名称

  background?: string,    // 背景色
  fallback?: () => void,  // 点击时的回调
}

const lawToolItems: ToolItem[] = [
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
    background: 'linear-gradient(150deg, #3a41ce, #1488CC)',
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
    fallback: notOpenNotification,
  },
]

const caseToolItems: ToolItem[] = [
  {
    icon: 'list',
    name: '案件管理',
    isLink: true,
    pathName: 'case',
    background: 'linear-gradient(150deg, #56ab2f, #a8e063)',
  },
  {
    icon: 'yen-sign',
    name: '违约金计算',
    background: 'linear-gradient(150deg, #ff4b1f, #ff9068)',
    fallback: notOpenNotification,
  }
]
</script>

<template>
  <div id="tools-main">
    <div class="card" style="width: 360px; ">
      <div class="card-title">法律法规相关</div>

      <el-row class="tools-box">
        <el-col :span="6" v-for="i in lawToolItems" style="display: flex; justify-content: center">
          <div class="tool-item" @click="i.fallback">
            <div class="tool-item-icon" :style="'background: ' + i.background">
              <font-awesome-icon :icon="i.icon"/>
            </div>
            <div class="tool-item-text">{{ i.name }}</div>

            <router-link v-if="i.isLink" :to="{ name: i.pathName }" class="tool-item-link"/>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="card" style="width: 360px; margin-left: 48px">
      <div class="card-title">案件相关</div>

      <el-row class="tools-box">
        <el-col :span="6" v-for="i in caseToolItems" style="display: flex; justify-content: center">
          <div class="tool-item" @click="i.fallback">
            <div class="tool-item-icon" :style="'background: ' + i.background">
              <font-awesome-icon :icon="i.icon"/>
            </div>
            <div class="tool-item-text">{{ i.name }}</div>

            <router-link v-if="i.isLink" :to="{ name: i.pathName }" class="tool-item-link"/>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "tools-page"
}
</script>

<style scoped>
#tools-main {
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

.tool-item {
  width: 84px;
  margin: 12px 0;
  color: var(--text-color);
  user-select: none;
  cursor: pointer;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tool-item:hover .tool-item-text {
  color: var(--theme-color);
}

.tool-item-icon {
  width: 56px;
  height: 56px;
  border-radius: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #8e9eab;
  color: rgba(255, 255, 255, 0.9);
  font-size: 28px;
}

.tool-item-text {
  margin-top: 8px;
  font-size: 14px;
  transition: all 0.2s;
}

.tool-item-link {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
