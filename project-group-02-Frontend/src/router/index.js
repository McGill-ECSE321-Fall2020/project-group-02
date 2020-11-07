import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login/Login'
import Signup from '@/components/Signup/Signup'

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      name: 'Signup',
      component: Signup
    },

    {
      path: '/login',
      name: 'Login',
      component: Login
    }

  ]
})
