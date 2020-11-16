<template>
  <div class="vue-template vertical-center inner-block">
    <form>
      <h3>Login</h3>
      <div class="form-group">
        <label>Username</label>
        <input
          v-model="userName"
          type="text"
          class="form-control form-control-lg"
        />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input
          v-model="userPassword"
          type="password"
          class="form-control form-control-lg"
        />
      </div>

      <a href=""
        @click.prevent="loginUser"
        type="submit"
        class="btn btn-dark btn-lg btn-block"
        :disabled="!userName || !userPassword"
      >
        Sign In
      </a>
    </form>
  </div>
</template>

<script>
import Vue from "vue";

export default {
  name: "login",
  created: function () {
    this.AXIOS.get("/art-gallery-system")
      .then((response) => {
        this.artGallerySystem = response.data;
      })
      .catch((error) => {
        this.userError = error;
      });
  },
  data() {
    return {
      artGallerySystem: "",
      userName: "",
      userPassword: "",
      user: "",
      userError: "",
      LoggedIn: false,
    };
  },
  methods: {
    loginUser: function () {
      this.AXIOS.post(
        "/user-login?username=".concat(this.userName) +
          "&password=".concat(this.userPassword)
      )
        .then((response) => {
          this.$store.state.user.loggedIn = response.data;
          this.LoggedIn = response.data;
          if (!this.$store.state.user.loggedIn) {
            alert("Invalid User Credentials");
          } else {
            this.$router.push("/collections");
            this.AXIOS.get(
              "/user-by-name/".concat(this.userName)
            ).then((response) => {
              let user = {
                username: "",
                email: "",
                password: "",
                address: {
                  street: "",
                  postalCode: "",
                  province: "",
                  country: "",
                  city: "",
                },
                paymentCredentials: {},
                userRole: [],
                loggedIn: false,
              };
              user = response.data;
              user.username = response.data.username;
              user.email = response.data.email;
              user.password = response.data.password;
              user.address = response.data.addresses;
              user.userRole = response.data.userRole;
              user.loggedIn = response.data.isLoggedIn;

              this.$store.commit("setUser", user);
            });
          }
        })
        .catch((error) => {
          alert("Such user does not exist");
        });
    },
  },
};
</script>


<style scoped>
body {
  background: #fff !important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
}

body,
html,
.App,
.vue-template,
.vertical-center {
  width: 100%;
  height: 100%;
}

.navbar-light {
  background-color: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
}

.vertical-center {
  display: flex;
  text-align: left;
  justify-content: center;
  flex-direction: column;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 65%;
}

.inner-block {
  width: 450px;
  margin: auto;
  background: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
  padding: 40px 55px 45px 55px;
  border-radius: 15px;
  transition: all 0.3s;
}

.vertical-center .form-control:focus {
  border-color: #2554FF;
  box-shadow: none;
}

.vertical-center h3 {
  text-align: center;
  margin: 0;
  line-height: 1;
  padding-bottom: 20px;
}

label {
  font-weight: 500;
}

.forgot-password,
.forgot-password a {
  text-align: right;
  font-size: 13px;
  padding-top: 10px;
  color: #7a7a7a;
  margin: 0;
}

.forgot-password a {
  color: #2554FF;
}

.social-icons {
  text-align: center;
  font-family: "Open Sans";
  font-weight: 300;
  font-size: 1.5em;
  color: #222222;
}

.social-icons ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.social-icons ul li {
  display: inline-block;
  zoom: 1;
  width: 65px;
  vertical-align: middle;
  border: 1px solid #e3e8f9;
  font-size: 15px;
  height: 40px;
  line-height: 40px;
  margin-right: 5px;
  background: #f4f6ff;
}

.social-icons ul li a {
  display: block;
  font-size: 1.4em;
  margin: 0 5px;
  text-decoration: none;
}
.social-icons ul li a i {
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

.social-icons ul li a:focus i,
.social-icons ul li a:active i {
  transition: none;
  color: #222222;
}
</style>
