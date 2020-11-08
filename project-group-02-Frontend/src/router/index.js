import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login/Login'
import Signup from '@/components/Signup/Signup'
import Item from "../components/Items/Item";
import ItemsList from "../components/Items/ItemsList";
import CollectionsList from "../components/Collections/CollectionsList";

Vue.use(Router)

let CollectionsPage;
export default new Router({
  routes: [
    {
      path: '/',
      name: 'signup',
      component: Signup
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/collections',
      name: 'collectionsList',
      component: CollectionsList
    },
    {
      path: '/collections/:collection',
      name: 'itemsList',
      component: ItemsList
    }
  ]
})
