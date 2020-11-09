<template>
    <div id="Checkout">
<div class="container">
  <div class="card">
    <button class="proceed"><svg class="sendicon" width="24" height="24" viewBox="0 0 24 24">
  <path d="M4,11V13H16L10.5,18.5L11.92,19.92L19.84,12L11.92,4.08L10.5,5.5L16,11H4Z"></path>
</svg></button>
   <img src="https://seeklogo.com/images/V/VISA-logo-62D5B26FE1-seeklogo.com.png" class="logo-card">

   <label>

   </label>
 <label class="cardnumber">Choose card:</label>
  <select name="select card" id="userCard">
    <option value="Card1">1234 5678 9123 4567</option>
    <option value="Card2">1234 5678 9512 5879</option>
  </select>
  </div>
  <div class="receipt">
      <div class="row">
      <div class="column">
        <b-icon-type-h3>Choose delivery:</b-icon-type-h3>
<select class="delivery" name="delivery" id="delivery">
    <option value="delivery1">In store pickup</option>
    <option value="delivery2">Home delivery</option>
  </select>
      <button class="return">Cancel</button>
      </div>
      <div class="column">
      <p>Subtotal:</p>
      <p>Taxes:</p>
      <p>____________________</p>
      <label>

      </label>
      <h3>Total:</h3>
      </div>
      </div>

  </div>
</div>
    </div>
</template>



<style>

@import url('https://fonts.googleapis.com/css?family=Work+Sans');
body{
font-family: 'Work Sans', sans-serif;
background: #00d2ff;
background: -webkit-linear-gradient(to right, #fff, #fff);
background: linear-gradient(to right, #fff, #fff);
}

.card{
  background:#16181a;  border-radius:14px; max-width: 300px; display:block; margin:auto;
  padding:60px; padding-left:20px; padding-right:20px;box-shadow: 2px 10px 40px black; z-index:99;
}

.logo-card{
  max-width:50px; margin-bottom:15px; margin-top: -19px;
}

label{
  display:flex; font-size:10px; color:white; opacity:.4;
}

select{
  padding-right:50px; font-size:20px; font-family: 'Work Sans', sans-serif; background:transparent; border:none; color:#dbdce09d; -webkit-appearance: none;
    -moz-appearance: none;
    text-indent: 1px;
    text-overflow: '';
}
select:focus{border-bottom:1px solid #1abc9c; outline:none;}

.delivery{ background:#16181a00; border-radius:6px; color:#000000;box-shadow: 1px 3px 10px rgba(0, 0, 0, 0.288);}

.return{border: none; margin-top: 60%; font-size:20px; font-family: 'Work Sans', sans-serif; background:#d42c2c1c; border-radius:6px;}
.return:hover{background:#d42c2c6b}

.cardnumber{padding-left:13px; font-size:15px; margin-bottom:8px; }

.name{display:block; font-size:15px; max-width: 200px; float:left; margin-bottom:15px;}

.toleft{float:left;}
.ccv{width:50px; margin-top:-5px; font-size:15px;}

.receipt{background: #ffffff; border-radius:4px; padding:5%; padding-top:250px; max-width:600px; margin:auto; margin-top:-180px;color:#000000;box-shadow: 1px 3px 10px rgba(0, 0, 0, 0.288);}

.column {
  padding-left: 10%;
  text-align: left;
  float: left;
  width: 50%;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.cost{color:#3a7bd5;}
.seller{color: #3a7bd5;}
.description{font-size: 13px;}
.price{font-size:12px;}
.comprobe{text-align:center;}
.proceed{position:absolute; transform:translate(300px, 10px); width:50px; height:50px; border-radius:50%; background:#1abc9c; border:none;color:white; transition: box-shadow .2s, transform .4s; cursor:pointer;box-shadow: 1px 3px 10px rgba(0, 0, 0, 0.288);}
.proceed:active{outline:none; box-shadow: 1px 3px 10px rgba(0, 0, 0, 0.288);}
.proceed:focus{outline:none; box-shadow: inset 0px 0px 5px white;}
.sendicon{filter:invert(100%); padding-top:2px;}

@media (max-width: 600px){
  .proceed{transform:translate(250px, 10px);}
  .col{display:block; margin:auto; width:100%; text-align:center;}
}
</style>

<script>
export default {
  name: "Checkout",
  props: {

  },
  data() {
    return {

    }
  },
  methods: {

  }
}
</script>
