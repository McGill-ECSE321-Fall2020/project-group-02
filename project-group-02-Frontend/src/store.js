import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export const store = new Vuex.Store({
  plugins: [
    createPersistedState({

    })
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
      paymentCredentials: [],
      userRole: [],
      loggedIn: false,
    }
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
    }
  },
  getters: {
    getUser: (state) => {
      return store.state.user;
    }
  }
});
