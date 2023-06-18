<script setup lang="ts">

import { ref }            from "vue";
import { router }         from "../router/index";
import { store }          from "../store/index";
import { login }          from "../services/user";
import { ElNotification } from "element-plus";

const username = ref<string>('')
const password = ref<string>('')

const onLogin = () => {
  if (username.value === '') {
    ElNotification({
      title: '登录失败',
      message: '用户名不能为空',
      type: 'error'
    })
    return
  }

  if (password.value === '') {
    ElNotification({
      title: '登录失败',
      message: '密码不能为空',
      type: 'error'
    })
    return
  }

  login({username: username.value, password: password.value})
      .then(res => {
        store.commit('setState', res)
        localStorage.setItem('token', res.token)
        localStorage.setItem('username', res.username)

        ElNotification({
          title: '登录成功',
          message: '欢迎回来，' + res.username,
          type: 'success'
        })

        router.push({path: '/main/tools'})
      })
      .catch(err => {
        console.log(err)
      })
}
</script>

<template>
  <div id="login">
    <div id="login-box">
      <p style="margin-top: 48px; font-size: 18px; font-weight: bold; color: var(--theme-color)">用户登录</p>

      <p style="margin: 48px 0 0 4px; font-size: 12px; color: var(--text-gray-color)">用户名</p>
      <el-input v-model="username" style="margin-top: 8px"/>

      <p style="margin: 16px 0 0 4px; font-size: 12px; color: var(--text-gray-color)">密码</p>
      <el-input v-model="password" style="margin-top: 8px" type="password"/>

      <el-button style="margin-top: 48px; width: 100%; background: var(--theme-color); color: #ffffff" @click="onLogin">
        登录
      </el-button>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "login-page"
}
</script>

<style scoped>
#login {
  width: 100%;
  height: 100vh;
  background: #E1E8EE;
  display: flex;
  justify-content: center;
  align-items: center;
}

#login-box {
  width: 360px;
  height: 420px;
  background: #ffffff;
  border-radius: 8px;
  box-sizing: border-box;
  padding: 0 48px;
}
</style>
