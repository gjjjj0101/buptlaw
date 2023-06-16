import { createApp } from 'vue'

import { library }         from "@fortawesome/fontawesome-svg-core"
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fab }             from "@fortawesome/free-brands-svg-icons"
import { fas }             from "@fortawesome/free-solid-svg-icons"

library.add(fab)
library.add(fas)

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import { router } from './router'
import { store } from './store'

const app = createApp(App)

app.use(ElementPlus as any)
app.use(router)
app.use(store)
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
