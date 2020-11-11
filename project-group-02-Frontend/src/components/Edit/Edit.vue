<script src="./artGallery.js">
</script>

<template>
    <div class="vue-template vertical-center-edit inner-block">
        <form>
            <br></br>
            <h1 style="text-align:center">Edit My Profile</h1>
         
            <br></br>

            <h3>Change My Address</h3>
            <div class="form-group">
                <label>Street</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Postal Code</label>
                <input type="address" class="form-control form-control-lg" />
                <label>City</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Province</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Country</label>
                <input type="address" class="form-control form-control-lg" />
            </div>
      <br></br>
            <h3>Update My Credentials</h3>
            <div class="form-group">
            
                <label>Card Holder Name</label>
                <input type="text" class="form-control form-control-lg"/>
                <label>Credit Card Number</label>
                <input type="text" class="form-control form-control-lg" />
                <label>Expiration Date</label>
                <input type="Date" class="form-control form-control-lg" />
                <label>Card Verification Code</label>
                <input type="password" class="form-control form-control-lg" />
            </div>

            <router-link :to="{name: 'profile'}">

                    <button type="submit" class="btn btn-dark btn-lg btn-block" >Submit</button>

            </router-link>

        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {}
        }
    }
</script>




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
  top: 80%;
  transform: translate(-50%, -50%);
  height: 160%;
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
var config = require('../../config')

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
            street: '',
            postalCode: '',
            city: '',
            province: '',
            country: '',
            password: '',
            paymentCredentials: '',
            error: ''
        }
    },

    created: function () {
        AXIOS.get('/user-by-name' + '/'.concat($username))
            .then(response => {
                this.user = response.data
            })
            .catch(e => {
                this.error = e;
            });
    },

    methods: {
        findUsername: function () {
            AXIOS.get('/create-user' + '/'.concat($username))
                .then(response => {
                    this.username = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserEmail: function () {
            AXIOS.get('/create-user' + '/'.concat($username) + '/email') // do /email??? or concat??
                .then(response => {
                    this.email = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserPassword: function () {
            AXIOS.get('/create-user' + '/'.concat($username) + '/email/pw')
                .then(response => {
                    this.password = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserStreet: function () {
            AXIOS.get('/update-user-address' + '/'.concat($username) + '/street')
                .then(response => {
                    this.street = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserPostalCode: function () {
            AXIOS.get('/update-user-address' + '/'.concat($username) + '/street/postalcode')
                .then(response => {
                    this.postalCode = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserProvince: function () {
            AXIOS.get('/update-user-address' + '/'.concat($username) + '/street/postalcode/province')
                .then(response => {
                    this.province = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserCountry: function () {
            AXIOS.get('/update-user-address' + '/'.concat($username) + '/street/postalcode/province/country')
                .then(response => {
                    this.country = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        findUserCity: function () {
            AXIOS.get('/update-user-address' + '/'.concat($username) + '/street/postalcode/province/country/city')
                .then(response => {
                    this.city = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
    }
}
<\script>