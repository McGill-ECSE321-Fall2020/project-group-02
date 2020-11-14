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
        <p>{{ $store.state.user.username }}</p>
          
      </div>

      <div class="form-group">
        <label>Email address</label>
           <p>{{ $store.state.user.email }}</p>
      </div>

      <div class="form-group">
        <label>Password</label>
          <p>{{ $store.state.user.password }}</p>
      </div>

      <!-- <div v-if="this.$store.state.user.email != 'ahmad.siddiqi@hotmail.com'" class="form-group">
       
        <label>Address</label>
          <p>{{ $store.state.user.address.street }}
         
          </p>
    
      </div>

         <div v-if="this.$store.state.user.userRole[0].userRoleId === ((this.$store.state.user.usernmae).concat('customer')).hashCode()" class="form-group">
        <label>Payment Credentials</label>
        <p>{{ $store.state.user.paymentCredentials }}</p>
      </div>-->

      <router-link :to="{name: 'collectionsList'}">
        <button type="submit" class="btn btn-dark btn-lg btn-block">Visit Our Catalog</button>
      </router-link>

      <p class="forgot-password text-right">
        Edit your profile
         <router-link :to="{name: 'edit'}">
         <a href="/#/edit">here</a>
      </router-link>
      

      </p>

      <div v-if="userError"> {{ userError }}</div>

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
      credentials: '',
      userError: ""
    }
  },
  created: function () {
    this.user = new ApplicationUserDTO()

    AXIOS.get('/user-by-name/'.concat($username))
      .then(response => {
        this.user = response.data;
      })
      .catch(error => {
        this.userError = "There was a problem fetching the user information";
      })
  },
  methods: {
    findUsername: function () {
      this.AXIOS.get('/user-by-name' + '/'.concat($username))
        .then(response => {
          this.username = $username
        })
        .catch(e => {
          this.error = e;
        });
    },
    findUserEmail: function () {
      this.AXIOS.get('/user-by-name' + '/'.concat($username))
        .then(response => {
          this.email = $user.email
        })
        .catch(e => {
          this.error = e;
        });
    },
    findUserPassword: function () {
      this.AXIOS.get('/user-by-name' + '/'.concat($username))
        .then(response => {
          this.password = $user.password
        })
        .catch(e => {
          this.error = e;
        });
    },
    findUserAddress: function () {
      this.AXIOS.get('/user-by-name' + '/'.concat($username))
        .then(response => {
          this.address = $user.address
        })
        .catch(e => {
          this.error = e;
        });
    },
    findUserCredentials: function () {
      this.AXIOS.get('/user-by-name' + '/'.concat($username))
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
