<script src="./artGallery.js">
</script>

<template>
  <div id="app">

    <Header style="margin-bottom: 50px"></Header>

    <div id="cart">
      <div id="head">
        <h3 style="margin-bottom: 50px">Shopping Cart</h3>
        <div class="row">
          <div class="col">
            <div>Item</div>
          </div>
          <div class="col">
            <div id="price">Price</div>
          </div>
          <div class="col">
            <div id="quantity">Quantity</div>
          </div>
          <div class="col">
          </div>
          <div class="col">
            <div id="total">Total</div>
          </div>
        </div>
      </div>
    </div>

    <hr>

    <div id="buy-box">
      <div v-for="item in items" :key="item.name" style="margin:20px;">
        <div class="row">

          <div class="col">
            <img class="images1" v-bind:src=item.itemURL>
            <h4 style="font-size:medium">{{item.itemName}}</h4>
          </div>
          <div class="col centering">
            <p>$ {{item.itemPrice}}</p>
          </div>

          <div class="col centering">

            <div style="display: inline-block" class="qty">{{item.itemQty}}</div>

          </div>
          <div class="col centering">
            <div class="del" v-on:click="removeItem(item)">Remove</div>
          </div>
          <div class="col centering">
            <div class="totalprice">{{item.itemPrice}}</div>
          </div>

        </div>
      </div>
    </div>

    <hr>

    <div class="row">

      <div class="col">
        <h2>Total : </h2>

      </div>

      <div class="col">

        <div>{{this.itemsTotal}}</div>

      </div>

    </div>

    <router-link :to="{name: 'checkout'}">
      <div class="row">

        <button style="margin-top: 50px" type="submit" class="btn btn-dark btn-lg btn-block">Checkout</button>

      </div>
    </router-link>
  </div>

</template>




  <script>

    import Header from "../Header/Header";

    export default {
      name: "ShoppingCart",
      components: {
        Header,
      },
      data() {
        return {
          
          itemsTotal: 0,
          collection: 'The Secrets of the Intelligence',
          items: [
            {
              itemName: "The Peaceful Mind",
              artistName: "Picasso",
              itemPrice: 10000,
              itemURL: "https://picsum.photos/300/300/?image=41",
              itemQty: 1,
            },
            {
              itemName: "My Dear Mother",
              artistName: "Michelangelo",
              itemPrice: 12000,
              itemURL: "https://picsum.photos/300/300/?image=41",
              itemQty: 1,
            },
            {
              itemName: "The Outside",
              artistName: "Leonardo Da Vinci",
              itemPrice: 14000,
              itemURL: "https://picsum.photos/300/300/?image=41",
              itemQty: 1,
            }
 
          ],

          
        }
      },
      methods: {
        removeItem: function (item) {
          var index = this.items.indexOf(item);
          this.items.splice(index, 1);
          this.itemsTotal -= item.itemPrice;
          
        }

      },
      mounted: function(){
        for(let i = 0; i <this.items.length; i++) {
      this.itemsTotal += this.items[i].itemPrice;
        }
      }
      
    }
  </script>

  <style>

    html, body {
      background-color: #eee;
      font-family: calibri, sans-serif;
    }

    #app {
      width: 760px;
      margin: 20px auto;
      #product{

      .box {
      width: 230px;
      background-color: #fff;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      display: inline-block;
      margin: 0 10px;
      position: relative;
      img{
      width: 230px;
    }

    i {
      width: 50px;
      height: 50px;
      background: #ED277F;
      color: #ffffff;
      border-radius: 25px;
      text-align: center;
      line-height: 50px;
      font-size: 1.4rem;
      position: absolute;
      right: 20px;
      top: 150px;
      box-shadow: 0 0 4px 2px rgba(80, 80, 80, 0.1);
      cursor: pointer;
      transition: all 0.3s;
      &:hover{
      transform: scale(1.05);
      }
    }

    h2 {
      margin-left: 20px;
    }

    p {
      margin-left: 20px;
    }

    }
    }

    #cart {
      margin-top: 50px;
      overflow: hidden;
      #head{
      width: 100%;
      border-bottom: 1px solid #BFBFBF;
      height: 40px;
      display: block;
      h3: {
      display: inline-block;
      line-height: 40px;
      margin: 0;
    };

    #price {
      display: inline-block;
      color: #777777;
      margin-left: 200px;
      line-height: 40px;
    }

    #quantity {
      display: inline-block;
      color: #777777;
      margin-left: 100px;
      line-height: 40px;
    }

    #total {
      display: inline-block;
      color: #777777;
      line-height: 40px;
      float: right;
    }

    }

    .row {
      width: 100%;
      border-bottom: 1px solid #BFBFBF;
      overflow: hidden;
      padding: 10px 0;
      display: block;
      float: left;
      img{
      height: 100px;
      float: left;
    }

    h4 {
      float: left;
      margin: 0;
      line-height: 100px;
      margin-left: 20px;
      width: 100px;
    }

    p {
      float: left;
      margin: 0;
      width: 80px;
      line-height: 100px;
      margin-left: 35px;
      text-align: center;
    }

    .qty-minus {
      float: left;
      width: 20px;
      line-height: 100px;
      margin-left: 60px;
      text-align: center;
      cursor: pointer;
    }

    .qty {
      float: left;
      width: 20px;
      line-height: 100px;
      margin-left: 20px;
      text-align: center;
    }

    .qty-plus {
      float: left;
      width: 20px;
      line-height: 100px;
      margin-left: 20px;
      text-align: center;
      cursor: pointer;
    }

    .del {
      float: left;
      width: 80px;
      line-height: 100px;
      margin-left: 60px;
      cursor: pointer;
      text-decoration: underline;
      color: #ED277F;
    }

    .totalprice {
      float: left;
      width: 80px;
      line-height: 100px;
      margin-left: 10px;
      text-align: right;
    }

      .row p::before, .box p::before, .totalprice::before {
        content: "$";
      }

    }

    h5 {
      font-size: 1.2rem;
      text-align: right;
    }

    }
    }

    .images1{
      width: 100px;
      margin-bottom: 20px;
      border-radius: 15%;
    }

    element.style {
      width: 100px;
    }

  
  </style>
