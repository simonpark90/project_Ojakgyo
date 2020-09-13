import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Home.vue')
  },
  {
    path: '/Signin',
    name: 'Signin',
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Signin.vue')
  },
  {
    path: '/Signup',
    name: 'Signup',
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Signup.vue')
  },
  {
    path: '/Artwork',
    name: 'Artwork',
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Artwork.vue')
  },
  {
    path: '/Profile',
    name: 'Profile',
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Profile.vue')
  },
  {
    path: '/Upload',
    name: 'Upload',
  
    component: () => import(/* webpackChunkName: "ojakgyo" */ '@/views/Upload.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
