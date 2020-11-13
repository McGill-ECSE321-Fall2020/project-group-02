<script src="./artGallery.js">
</script>

<template>
    <div class="vue-template vertical-center-profile inner-block">
        <form>
           <br>
            <h1 style="text-align:center">My Profile</h1>
               <br>

            <div class="form-group">
                <label>Username</label>
                <input @click="findUsername" type="text" id="username" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Email address</label>
                <input @click="findUserEmail" type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input @click="findUserPassword" type="text" id="password" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Address</label>
                <input @click="findUserAddress" type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Payment Credentials</label>
                <input @click="findUserCredentials" type="text" id="payment" class="form-control form-control-lg" readonly />
            </div>

            <router-link :to="{name: 'collectionsList'}">
                <button type="submit" class="btn btn-dark btn-lg btn-block">Visit Our Catalog</button>
            </router-link>

            <p class="forgot-password text-right">
                Edit your profile
                <a href="/#/edit" >here</a>

            </p>

            <div v-if = "userError"> {{userError}} </div>

        </form>
    </div>
</template>




<style>

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


.vertical-center-profile {
  display: flex;
  text-align: left;
  justify-content: center;
  flex-direction: column;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 100%;
}
.vertical-center-edit {
  display: flex;
  text-align: left;
  justify-content: center;
  flex-direction: column;
  position: absolute;
  left: 50%;
  top: 75%;
  transform: translate(-50%, -50%);
  height: 180%;
}

.inner-block {
  width: 450px;
  margin: auto;
  background: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
  padding: 40px 55px 45px 55px;
  border-radius: 15px;
  transition: all .3s;
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


<script>
import axios from 'axios'
import Profile from "./Profile";
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function AddressDTO(street, postalCode, province, city, country) {
    this.street = street;
    this.postalCode = postalCode;
    this.province = province;
    this.city = city;
    this.country = country;
}

function ApplicationUserDTO(accountCreationDate, username, email, password, phoneNumber, userRoles, isLoggedIn, ags, bal) {
    this.accountCreationDate = accountCreationDate;
    this.username = username;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.artGallerySystem = ags;
    this.userRoles = ur;
    this.isLoggedIn = isLoggedIn;
    this.balance = bal;
}



export default {
    name: 'Profile',
    data() {
        return {
            user: '',
            email: '',
            username: '',
            address: '',
            password: '',
            paymentCredentials: '',
            error: '',
            credentials: ''
        }
    },

    created: function () {
        AXIOS.get('/user-by-name' + '/'.concat($username))
            .then(response => {
                this.user = $user
            })
            .catch(e => {
                this.error = e;
            });
    },

    methods: {
        findUsername: function () {
            AXIOS.get('/user-by-name' + '/'.concat($username))
                .then(response => {
                    this.username = $username
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserEmail: function () {
            AXIOS.get('/user-by-name' + '/'.concat($username))
                .then(response => {
                    this.email = $user.email
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserPassword: function () {
            AXIOS.get('/user-by-name' + '/'.concat($username))
                .then(response => {
                    this.password = $user.password
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserAddress: function () {
            AXIOS.get('/user-by-name' + '/'.concat($username))
                .then(response => {
                    this.address = $user.address
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserCredentials: function () {
            AXIOS.get('/user-by-name' + '/'.concat($username))
                .then(response => {
                    this.credentials = $user.credentials
                })
                .catch(e => {
                    this.error = e;
                });
        },
    }
}

</script>