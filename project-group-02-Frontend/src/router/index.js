import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login/Login'
import Signup from '@/components/Signup/Signup'
import Item from "../components/Items/Item";
import ItemsList from "../components/Items/ItemsList";
import CollectionsList from "../components/Collections/CollectionsList";
import Profile from "../components/Profile/Profile";
import Edit from "../components/Edit/Edit";
import Checkout from "../components/Checkout/Checkout";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import UploadArt from "../components/UploadArt/UploadArt";
import CreateCollection from "../components/CreateCollection/CreateCollection";
import ShoppingCart from "../components/ShoppingCart/ShoppingCart";

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
    },
    {
      path: '/checkout',
      name: 'checkout',
      component: Checkout
    },
    {
      path: '/profile/:username',
      name: 'profile',
      component: Profile
    },
    {
      path: '/edit',
      name: 'edit',
      component: Edit
    },
    {
      path: '/header',
      name: 'header',
      component: Header
    },
    {
      path: '/footer',
      name: 'footer',
      component: Footer
    },
    {
      path: '/upload_art',
      name: 'UploadArt',
      component: UploadArt
    },
    {
      path: '/create_collection',
      name: 'CreateCollection',
      component: CreateCollection
    },
    {
      path: '/shopping-cart',
      name: 'ShoppingCart',
      component: ShoppingCart
    }
  ]
})
