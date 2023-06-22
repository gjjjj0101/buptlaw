<script setup lang="ts">
import { ref, nextTick }  from "vue";
import {
  Close,
  Promotion,
}                         from '@element-plus/icons-vue'
import { ElNotification } from "element-plus";

const text               = ref<string>('')
const messageDisplayList = ref<{ role: number, content: string }[]>([])

const openDrawer = () => {
  let drawer = document.getElementById('robot-drawer')
  if (!drawer) return

  drawer.style.transform  = 'translateX(-100px)'
  drawer.style.opacity    = '1'
  drawer.style.visibility = 'visible'

  if (!messageDisplayList.value.length) {
    messageDisplayList.value.push({
      role: 0,
      content: '您好，有什么可以帮助您',
    })
  }
}

const closeDrawer = () => {
  let drawer = document.getElementById('robot-drawer')
  if (!drawer) return
  drawer.style.transform  = 'translateX(0)'
  drawer.style.opacity    = '0'
  drawer.style.visibility = 'hidden'
}

const sendMessage = () => {
  if (!text.value) {
    ElNotification({
      title: '发送失败',
      message: '消息不能为空',
      type: 'error',
    })
    return
  }

  messageDisplayList.value.push({
    role: 1,
    content: text.value,
  })
  text.value = ''

  // TODO: 接入ChatGPT，并将下面的代码替换为ChatGPT的调用
  messageDisplayList.value.push({
    role: 0,
    content: '测试消息，暂未接入ChatGPT',
  })

  nextTick(() => {
    let elem = document.getElementById('messageBox')
    if (elem) elem.scrollTop = elem.scrollHeight
  })
}
</script>

<template>
  <div id="ai-chat">
    <div id="ai-chat-button" @click="openDrawer">
      <div id="ai-chat-button-icon">
        <font-awesome-icon icon="magnifying-glass"/>
      </div>
      <div id="ai-chat-button-text">AI&#10;智&#10;能&#10;问&#10;答</div>
    </div>

    <el-affix style="position: absolute; left: -12px; top: 0">
      <div class="robot-drawer" id="robot-drawer">
        <el-button :icon="Close" @click="closeDrawer" class="close-drawer-button" circle size="large"/>
        <p style="color: var(--text-color); text-align: center; font-size: 20px; font-weight: bold; margin: 12px 0 32px">
          聊天</p>

        <div style="height: 60%; overflow-y: scroll; overflow-x: hidden; " id="messageBox">
          <div v-for="(i) in messageDisplayList" :class="i.role === 0 ? 'left-message' : 'right-message'">
            <div>
              <p style="width: 100%; margin: 0">{{ i.content }}</p>
            </div>
          </div>
        </div>

        <el-divider style="margin-bottom: 8px"/>
        <div style="display: flex; justify-content: center">
          <div style="width: 100%">
            <el-input
                v-model="text"
                type="textarea"
                :autosize="{ minRows: 5, maxRows: 5 }"
                placeholder="来问点什么吧"
                resize="none"
                @keydown.ctrl.enter.native="sendMessage"
            />
            <div style="margin-bottom: 8px"/>

            <div style="text-align: right">
              <el-button :icon="Promotion" type="primary" style="width: 80px" @click="sendMessage">发送</el-button>
            </div>
          </div>
        </div>
      </div>
    </el-affix>

  </div>
</template>

<script lang="ts">
export default {
  name: "ai-chat"
}
</script>

<style scoped>
#ai-chat {
  position: absolute;
  top: 120px;
  right: 24px;
  z-index: 9;
}

#ai-chat-button {
  width: 48px;
  height: 200px;
  background: linear-gradient(180deg, #4096ff, #45A6F7, #8dc6f6);
  border-radius: 24px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #ffffff;
  cursor: pointer;
}

#ai-chat-button-icon {
  background: #ffffff;
  color: var(--theme-color);
  width: 36px;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 6px;
  border-radius: 18px;
  font-size: 18px;
}

#ai-chat-button-text {
  font-size: 22px;
  user-select: none;
}

.robot-drawer {
  position: absolute;
  right: -80px;
  width: 550px;
  height: 80vh;
  padding: 8px 24px;
  border-radius: 24px;
  background: #ffffff;
  box-shadow: var(--shadow-color) 0 8px 24px;
  border: 1px solid #ebebeb;
  overflow-y: auto;

  visibility: collapse;
  z-index: 2;

  transition: all 0.2s ease-in-out;
}

.close-drawer-button {
  position: absolute;
  top: 18px;
  right: 18px;
  width: 48px;
  height: 48px;
  font-size: 24px;
  float: right;
  border: none;
}

.close-drawer-button:hover {
  color: #7b7c80;
  background: rgba(0, 0, 0, 0.1);
}

.left-message {
  text-align: left;
  display: flex;
  justify-content: flex-start;
  margin-bottom: 24px;
  margin-left: 8px;
}

.right-message {
  text-align: left;
  display: flex;
  justify-content: flex-end;
  margin-bottom: 24px;
  margin-right: 24px;
}

.left-message div,
.right-message div {
  position: relative;
  width: max-content;
  max-width: 80%;

  padding: 8px 12px;
  border-radius: 8px;
}

.left-message div {
  background: #00B0FF;
  color: #FFFFFF;
  animation: slideFromLeft 0.2s ease-in-out;
}

@keyframes slideFromLeft {
  0% {
    margin-left: -100px;
    opacity: 0;
  }
  100% {
    margin-left: 0;
    opacity: 1;
  }
}

.right-message div {
  background: #ECEFF1;
  color: #222222;
  animation: slideFromRight 0.2s ease-in;
}

@keyframes slideFromRight {
  0% {
    margin-right: -100px;
    opacity: 0;
  }
  100% {
    margin-right: 0;
    opacity: 1;
  }
}

.left-message div:before,
.right-message div:before {
  content: '';

  width: 10px;
  height: 10px;
  display: block;

  transform: rotate(45deg);
  position: absolute;
  top: 14px;
}

.left-message div:before {
  background: #00B0FF;
  left: -5px;
}

.right-message div:before {
  background: #ECEFF1;
  right: -5px;
}
</style>
