import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export const store = new Vuex.Store({
  plugins: [
    createPersistedState({})
  ],
  state: {
    user: {
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
      paymentCredentials: {
        cardHolderName:'',
        ccNumber: '',
        expirationDate:'',
        cvc: ''
      },
      userRole: [],
      loggedIn: false,
    },
    totalPrice: 0,
  },
  mutations: {
    setUser: function (state, user) {
      store.state.user = user;
      store.state.user.username = user.username;
      store.state.user.email = user.email;
      store.state.user.password = user.password;
      store.state.user.address = user.address;
      store.state.user.userRole = user.userRole;
      store.state.user.loggedIn = user.isLoggedIn;
      store.state.user.paymentCredentials = user.paymentCredentials;
    },
    setPaymentCredentials: function (state, myPaymentCredentials) {
      store.state.user.paymentCredentials.cardHolderName = myPaymentCredentials.cardHolderName;
      store.state.user.paymentCredentials.ccNumber = myPaymentCredentials.ccNumber;
      store.state.user.paymentCredentials.expirationDate = myPaymentCredentials.expirationDate;
      store.state.user.paymentCredentials.cvc = myPaymentCredentials.cvc;
    },
  },
  getters: {
    getUser: (state) => {
      return store.state.user;
    },
    getPaymentCredentials: (state) => {
      return store.state.user.paymentCredentials;
    }
  }
});
