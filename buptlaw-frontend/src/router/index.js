import { createRouter, createWebHistory } from 'vue-router'


const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('../components/Home.vue')
    },
    {
        path:'/law',
        name:'Law',
        component: () => import('../components/Law.vue')
    }
]


const ip = ""
const router = createRouter({
    history: createWebHistory(ip),
    routes
})
export default router