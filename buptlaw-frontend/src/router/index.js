import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'

import Home from '../components/Home.vue'
import Test from '../components/Test.vue'
const router = createRouter({
    history: createWebHistory(), // hash 模式  ||   普通模式
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        // 路由怎么对应不上？   
        {
            path: '/test',
            name: 'test',
            component: Test
        },
    ]
})

export default router