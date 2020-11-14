<template>
  <nav>
    <ul>
      <li>
        <b-img src=""></b-img>
      </li>
      <li>
        <router-link to="/collections">
          <a>Home</a>
        </router-link>
      </li>
      <li v-if="this.$store.state.user.userRole[0].userRoleId === ((this.$store.state.user.username).concat('customer')).hashCode()">
        <router-link to="/shopping-cart">
        <a>Shopping Cart</a>
        </router-link>
      </li>
      <li  v-if="this.$store.state.user.userRole[0].userRoleId === ((this.$store.state.user.username).concat('artist')).hashCode()">
        <router-link to="/upload-art">
        <a>Upload Artwork</a>
        </router-link>
      </li>
      <li v-if="this.$store.state.user.email === 'ahmad.siddiqi@hotmail.com'">
        <router-link to="/create-collection">
        <a>Create Collection</a>
        </router-link>
      </li>
      <li>
        <router-link :to="{ name:'profile', params: { username: this.$store.state.user.username }}">
          <a>Account</a>
        </router-link>
      </li>
      <li>
          <a @click="logoutUser">
            <router-link to="/">
            Logout
            <!--{{this.$store.state.user}}
              <!--{{this.$store.state.user.userRole[0].userRoleId}}-->
            </router-link>
            </a>
      </li>
    </ul>
  </nav>
</template>

<script>
import Login from "../Login/Login";

export default {
  name: 'Header',
  
  data() {
    return {
      response: {} 
    }
  },
  methods: {
    logoutUser: function(){
      this.AXIOS.post("/user-logout/" .concat(this.$store.state.user.username))
      .then((response) => {
        this.$store.state.user.loggedIn = response.data;
        console.log(this.$store.state.user.loggedIn);
      })
      .catch((error) => {});
  }
}
}
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

html {
  height:100%;
  /*background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);*/
}

nav {
  max-width: 960px;
  mask-image: linear-gradient(90deg, rgba(255, 255, 255, 0) 0%, #ffffff 25%, #ffffff 75%, rgba(255, 255, 255, 0) 100%);
  margin: 0 auto;
  padding: 1px 0;
}

nav ul {
  text-align: center;
  background: linear-gradient(90deg, rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.2) 25%, rgba(255, 255, 255, 0.2) 75%, rgba(255, 255, 255, 0) 100%);
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
}

nav ul li {
  display: inline-block;
}

nav ul li a {
  padding: 18px;
  font-family: "Open Sans";
  text-transform:uppercase;
  color: rgba(0, 35, 122, 0.5);
  font-size: 18px;
  text-decoration: none;
  display: block;
}

nav ul li a:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
  background: rgba(255, 255, 255, 0.1);
  color: rgba(0, 35, 122, 0.7);
}

.topnav {
  background-color: #16181a;
  overflow: hidden;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 16px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}

.topnav input {
  float: left;
  background: #00000000;
  text-align: center;
  padding: 16px 16px;
  text-decoration: none;
  color: white;
  font-size: 17px;
  border: none;
}

.topnav input:hover {
  background-color: #ddd;
  color: black;
}

.topnav input.active {
  background-color: #4CAF50;
  color: white;
}
</style>
