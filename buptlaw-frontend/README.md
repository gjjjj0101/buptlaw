# 律所

## 前言

在接手这个项目的前端开发前，你需要

1. 对 HTML、JavaScript、CSS 有一定了解
2. 拥有对同步、异步、Promise、async、await 的基本认识
3. 对 JavaScript 的基本数据类型有一定了解，以此上手 TypeScript
4. 对 [Vue3](https://cn.vuejs.org/guide/introduction.html) 及其**生命周期**有一定了解
5. 对 [vue-router](https://router.vuejs.org/zh/) 、[vuex](https://vuex.vuejs.org/zh/guide/) 有一定了解
6. 熟悉 [Element-Plus](https://element-plus.gitee.io/zh-CN/) 和 [Font Awesome](https://fontawesome.com/) 组件库
7. 掌握 Git 的基本操作

同时你还需要理解本项目的结构

> - src （仅解释 src 文件夹中的重要内容）
>   - components（存放项目的 Vue 组件，但非页面组件）
>   - consts（存放常量）
>   - router（定义 router ，详细解释可学习 vue-router ）
>   - services（封装与后端的请求服务）
>   - store（定义 store ，详细解释可学习 vuex ）
>   - types（定义数据类型）
>   - utils（存放不与后端交互的一些基本通用方法）
>   - views（存放页面视图相关的 vue 组件，与 <router-view\/> 标签相关）

## 如何上手

**在继续往下之前，确保你对前端有一定了解或基础，并且已经完成了 *前言* 内容的学习**

为了能够充分理解整个项目的结构和封装，接下来你可以：

1. 阅读 `services/request.ts` 以及其他任意一个 `services` 文件夹下的 `ts` 文件

   > 这是运用 [axios](https://axios-http.com/zh/docs/intro) 封装 HTTP 请求的文件，在这一步你需要理解项目是如何封装 HTTP 请求的

2. 阅读 `router/index.ts`

   > 这个文件定义了项目各个页面的层级关系，但在阅读前，确保你了解过 vue-router 

3. 从 `App.vue` 文件开始捋顺整个项目的页面关系
