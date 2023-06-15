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
    component: () => import('../views/main-page.vue'),
    children: [
      {
        path: '/main',
        name: 'tools',
        component: () => import('../views/tool-page.vue'),
      },
      {
        path: '/main/law',
        name: 'law',
        component: () => import('../views/law-page.vue'),
      },
      {
        path: '/main/case',
        name: 'case',
        component: () => import('../views/case-page.vue'),
      },
    ]
  },
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
} as RouterOptions)
