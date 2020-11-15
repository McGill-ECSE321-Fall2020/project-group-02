<template>
  <div class="vue-template vertical-center inner-block">
    <form>
      <h3 style="padding: 20px">Sign Up</h3>

      <div class="form-group">
        <label>Username <b style="color:red;">*</b></label>
        <input
          v-model="userName"
          type="text"
          class="form-control form-control-lg"
        />
      </div>

      <div class="form-group">
        <label>Email address <b style="color:red;">*</b></label>
        <input
          v-model="userEmail"
          type="email"
          class="form-control form-control-lg"
        />
      </div>

      <div class="form-group">
        <label>Password <b style="color:#ff0000;">*</b></label>
        <input
          v-model="userPassword"
          type="password"
          class="form-control form-control-lg"
        />
      </div>

      <div class="row">
        <div class="col">
          <label class="">Street <b style="color:red;">*</b></label>
          <input
            v-model="userStreet"
            type="text"
            class="form-control form-control-lg"
          />
        </div>

        <div class="col">
          <label class="">Postal Code <b style="color:red;">*</b></label>
          <input
            v-model="userPostalCode"
            type="text"
            class="form-control form-control-lg"
          />
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label>City <b style="color:red;">*</b></label>
          <input
            v-model="userCity"
            type="text"
            class="form-control form-control-lg"
          />
        </div>

        <div class="col">
          <label>Province <b style="color:red;">*</b></label>
          <input
            v-model="userProvince"
            type="text"
            class="form-control form-control-lg"
          />
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label>Country <b style="color:red;">*</b></label>
          <input
            v-model="userCountry"
            type="text"
            class="form-control form-control-lg"
            style="margin-bottom: 10px"
          />
        </div>

        <div class="col">
          <label>Choose your role(s):</label>
          <br />
          <select name="roles" id="userrole">
            <option value="customer" id="customer">Customer</option>
            <option value="artist" id="artist">Artist</option>
          </select>
        </div>
      </div>

      <button
        v-if="validate"
        @click="createUser"
        type="submit"
        class="btn btn-dark btn-lg btn-block"
      >
        Sign Up
      </button>

        <button
          v-if="!validate"
          @click="
            setAddress();
            setUserRole();
          "
          :disabled="!userCity||!userStreet||!userPostalCode||!userProvince||!userCountry"
          type="submit"
          class="btn btn-dark btn-lg btn-block continue"
        >
          Confirm
        </button>

      <p class="forgot-password text-right" style="margin-bottom: 20px">
        Already registered
        <router-link :to="{ name: 'login' }">sign in?</router-link>
      </p>
      <div v-if="userError">
        {{ userError }}
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "Signup",
  data() {
    return {
      userName: "",
      userEmail: "",
      userPassword: "",
      userStreet: "",
      userPostalCode: "",
      userProvince: "",
      userCountry: "",
      userCity: "",
      userRoles: "",
      validate: true,
      userError:""
    };
  },
  methods: {
    checkForm: function (e) {
      this.formErrors = [];
      if (!this.userName) {
        this.errors.push("Name required.");
      }
      if (!this.userPassword) {
        this.errors.push("Password is required.");
      }
      if (!this.userEmail) {
        this.errors.push("Email required.");
      } else if (!this.validEmail(this.userEmail)) {
        this.errors.push("Valid email required.");
      }
      if (!this.formErrors.length) {
        return true;
      }
      e.preventDefault();
    },
    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    createUser: function () {
      this.AXIOS.post(
        "/create-user?name=".concat(this.userName) +
        "&email=".concat(this.userEmail) +
        "&pw=".concat(this.userPassword)
      )
        .then((response) => {
          this.validate = false;
        })
        .catch((error) => {
          alert("Invalid Username or Email");
        });
    },
    setAddress: function () {
      this.AXIOS.post(
        "/update-user-address/".concat(this.userName) +
        "?street=".concat(this.userStreet) +
        "&postalcode=".concat(this.userPostalCode) +
        "&province=".concat(this.userProvince) +
        "&country=".concat(this.userCountry) +
        "&city=".concat(this.userCity)
      )
        .then((response) => {
          this.$router.push('/login');
        })
        .catch((error) => {
          alert("Please enter a valid address");
        });
    },
    setUserRole: function () {
      var role = document.getElementById("userrole");
      var selectedValue = role.options[role.selectedIndex].value;
      if (selectedValue == "artist") {
        this.userRoles = "artist";
      } else {
        this.userRoles = "customer";
      }
      this.AXIOS.post(
        "/set-user-role/".concat(this.userName) +
        "?roles=".concat(this.userRoles)
      )
        .then((response) => {
        })
        .catch((error) => {});
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
  height: 75%;
}
.inner-block {
  width: 60%;
  margin: auto;
  background: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
  padding: 40px 55px 45px 55px;
  border-radius: 15px;
  transition: all 0.3s;
  height: 90%;
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
.inline-signup {
  display: inline-block;
}
.signup-right {
  padding-right: 100px;
}
.continue {
  background-color: green;
}
</style>
