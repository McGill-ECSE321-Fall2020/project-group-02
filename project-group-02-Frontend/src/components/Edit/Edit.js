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
