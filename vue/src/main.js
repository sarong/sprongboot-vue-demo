import { createApp } from 'vue'
import App from './App.vue'

import './assets/css/global.css'
import ElementPlus from 'element-plus'
// import 'element-plus/lib/theme-chalk/index.css'
import 'element-plus/dist/index.css'
import router from './router'

createApp(App).use(router).use(ElementPlus, { size: 'small' }).mount('#app')
