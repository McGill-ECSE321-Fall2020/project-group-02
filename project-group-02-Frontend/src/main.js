// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/assets/css/main.css'
import axios from "axios";

Vue.use(BootstrapVue)
Vue.config.productionTip = false;
Vue.prototype.$username = '';
Vue.prototype.$totalPrice = 0;
Vue.prototype.$user = {
  username: '',
  email: '',
  password: '',
  address: {
    street: '',
    postalCode: '',
    province: '',
    country: '',
    city: ''
  },
  paymentCredentials: [],
  userRole: [],
  loggedIn: false,
}
export const eventBus = new Vue(); // this is an event bus for handling events in a much easier fashion


let config = require('../config')
let frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
let backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
Vue.prototype.AXIOS = axios.create({
  baseURL: backendUrl,
  headers: {'Access-Control-Allow-Origin': frontendUrl}
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

