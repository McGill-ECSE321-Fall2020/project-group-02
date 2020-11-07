import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login/Login'
import Signup from '@/components/Signup/Signup'
import Item from "../components/Item";
import ItemsList from "../components/ItemsList";
import CollectionsList from "../components/CollectionsList";

Vue.use(Router)

let CollectionsPage;
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
    },
    {
      path: '/collections',
      name: 'CollectionsList',
      component: CollectionsList
    },
    {
      path: '/collections/collection/items',
      name: 'ItemsList',
      component: ItemsList
    },
    {
      path: '/'
    }

  ]
})
