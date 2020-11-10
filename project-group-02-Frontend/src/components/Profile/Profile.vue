<script src="./artGallery.js">
</script>

<template>
    <div class="vue-template vertical-center-profile inner-block">
        <form>
           <br></br>
            <h1 style="text-align:center">My Profile</h1>
               <br></br>

            <div class="form-group">
                <label>Username</label>
                <input type="text" id="username" class="form-control form-control-lg" readonly />

            </div>

            <div class="form-group">
                <label>Email address</label>
                <input type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="text" id="password" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Address</label>
                <input type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Payment Credentials</label>
                <input type="text" id="payment" class="form-control form-control-lg" readonly />
            </div>

            <router-link :to="{name: 'collectionsList'}">
                <button type="submit" class="btn btn-dark btn-lg btn-block">Visit Our Catalog</button>
            </router-link>

            <p class="forgot-password text-right">
                Edit your profile
                <a href="/#/edit" >here</a>

            </p>
        </form>
    </div>
</template>


<script>
    export default {
        data() {
            return {
            }
        },
      created: function () {

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
