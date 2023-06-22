import { createApp } from 'vue'

import { library }         from "@fortawesome/fontawesome-svg-core"
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fab }             from "@fortawesome/free-brands-svg-icons"
import { fas }             from "@fortawesome/free-solid-svg-icons"

library.add(fab)
library.add(fas)

import ElementPlus from 'element-plus'
import zhCn        from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

VueMarkdownEditor.use(vuepressTheme);

import App        from './App.vue'
import { router } from './router'
import { store }  from './store'

const app = createApp(App)


app.use(ElementPlus, {
  locale: zhCn,
})
app.use(router)
app.use(store)
app.use(VueMarkdownEditor);
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
