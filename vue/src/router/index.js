import { createRouter, createWebHistory } from 'vue-router'
import Content from '../views/Home'

const routes = [
  {
    path: '/',
    name: 'Content',
    component: Content
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
