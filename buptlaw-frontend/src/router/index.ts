//+-------------------------------------------------------------------------
//
//  Vue Router 配置
//
//  File:       index.ts
//
//  Directory:  buptlaw-frontend/src/router
//
//  History:    6月-13-2023   QQK  Created
//
//--------------------------------------------------------------------------
import { createRouter, createWebHashHistory, RouterOptions } from "vue-router";
import { store }                                             from "../store/index";

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login-page.vue'),
  },
  {
    path: '/main',
    name: 'main',
    redirect: '/main/tools',
    component: () => import('../views/main-page.vue'),
    children: [
      {
        path: '/main/tools',
        name: 'tools',
        component: () => import('../views/tool-page.vue'),
      },
      {
        path: '/main/law',
        name: 'law',
        component: () => import('../views/law-page.vue'),
      },
      {
        path: '/main/detail/:id',
        name: 'law-detail',
        component: () => import('../views/law-detail-page.vue'),
        props: true,
      },
      {
        path: '/main/case',
        name: 'case',
        redirect: '/main/case/analysis',
        component: () => import('../views/case-page.vue'),
        children: [
          {
            path: '/main/case/analysis',
            name: 'case-analysis',
            component: () => import('../views/case-analysis-page.vue'),
          },
        ]
      },
      {
        path: '/main/work',
        name: 'work',
        component: () => import('../views/work-page.vue'),
      },
      {
        path: '/main/record/:recordId',
        name: 'record',
        component: () => import('../views/work-record-page.vue'),
        props: true,
      }
    ]
  },
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
} as RouterOptions)

// 路由守卫
// 用于判断是否登录，如果没有登录则不允许访问其他页面，会被重定向到登录页面
router.beforeEach((to, from) => {
  // 用 store 里的 token 判断是否登录
  if (to.name !== 'login' && !store.state.token) {
    return '/login';
  }
})
