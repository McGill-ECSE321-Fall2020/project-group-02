<script src="./artGallery.js">
</script>

<template>
  <div class="vue-template vertical-center-edit inner-block">
    <form>
      <br />
      <h1 style="text-align: center">Edit My Profile</h1>

      <br />

      <h3>Change My Address</h3>
      <div class="form-group">
        <label>Street</label>
        <input
          v-model="userStreet"
          type="text"
          class="form-control form-control-lg"
        />
        <label>Postal Code</label>
        <input
          v-model="userPostalCode"
          type="text"
          class="form-control form-control-lg"
        />
        <label>City</label>
        <input
          v-model="userCity"
          type="text"
          class="form-control form-control-lg"
        />
        <label>Province</label>
        <input
          v-model="userProvince"
          type="text"
          class="form-control form-control-lg"
        />
        <label>Country</label>
        <input
          v-model="userCountry"
          type="text"
          class="form-control form-control-lg"
        />
      </div>
      <br />
      <!--<h3>Update My Credentials</h3>
            <div class="form-group">

                <label>Card Holder Name</label>
                <input v-model="cardHolderName" type="text" class="form-control form-control-lg"/>
                <label>Credit Card Number</label>
                <input v-model="creditCardNumber" type="text" class="form-control form-control-lg" />
                <label>Expiration Date</label>
                <input v-model="expirationDate" type="Date" class="form-control form-control-lg" />
                <label>Card Verification Code</label>
                <input v-model="cvc" type="password" class="form-control form-control-lg" />
            </div>-->

      <!--<router-link :to="{ name: 'profile' }">-->
        <button
          @click="setAddress()"
          :disabled="!userCity||!userStreet||!userPostalCode||!userProvince||!userCountry"
          type="submit"
          class="btn btn-dark btn-lg btn-block"
        >
          Submit
        </button>
     <!-- </router-link>-->
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


<script>
export default {
  name: "Edit",
  data() {
    return {
      userName: "",
      userStreet: "",
      userPostalCode: "",
      userProvince: "",
      userCountry: "",
      userCity: "",
      cardHolderName: "",
      creditCardNumber: "",
      expirationDate: "",
      cvc: "",
      paymentError: "",
    };
  },
  methods: {
    setAddress: function () {
      this.AXIOS.post(
        "/update-user-address/".concat(this.userName) +
          "?street=".concat(this.userStreet) +
          "&postalcode=".concat(this.userPostalCode) +
          "&province=".concat(this.userProvince) +
          "&country=".concat(this.userCountry) +
          "&city=".concat(this.userCity)
      )
        .then((response) => {})
        .catch((error) => {
          alert("Please enter a valid address");
        });
    },
    /* setPaymentCredentials: function () {
      this.AXIOS.post(
        "/update-user-address/".concat(this.$store.state.user.username) +
          "?cardHolderName=".concat(this.cardHolderName) +
          "&creditCardNumber=".concat(this.creditCardNumber) +
          "&expirationDate=".concat(this.expirationDate) +
          "&cvc=".concat(this.cvc) 
      )
        .then((response) => {})
        .catch((error) => {
          paymentError = error;
        });
    },*/
  },
};
</script>
