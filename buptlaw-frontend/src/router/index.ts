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
    name: 'main',
    component: () => import('../views/main-page.vue'),
  },
  {
    path: '/law',
    name: 'law',
    component: () => import('../views/law-page.vue'),
  }
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
} as RouterOptions)
