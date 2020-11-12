// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/assets/css/main.css'

Vue.use(BootstrapVue)
Vue.config.productionTip = false;
Vue.prototype.$username = '';
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
  userRole: []
}
export const eventBus = new Vue(); // this is an event bus for handling events in a much easier fashion



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

