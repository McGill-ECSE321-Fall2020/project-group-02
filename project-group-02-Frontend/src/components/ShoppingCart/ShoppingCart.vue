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
            <a href="" class="del" v-on:click.prevent="removeFromShoppingCart(item)">Remove</a>
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

        <div>{{this.$totalPrice}}</div>

      </div>

    </div>

   <router-link :to="{name: 'checkout'}">


        <button style="margin-top: 50px; margin-bottom: 50px" type="submit" class="btn btn-dark btn-lg">Checkout</button>

    </router-link>
    <Footer></Footer>
  </div>

</template>




  <script>
    import Header from "../Header/Header";
    import AXIOS from "../../App";
    import Footer from "../Footer/Footer";

    export default {
      name: "ShoppingCart",
      components: {
        Header,
        Footer,
      },
      data() {
        return {

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
        removeFromShoppingCart: function (item) {
          var index = this.items.indexOf(item);
          this.items.splice(index, 1);
          this.$totalPrice -= item.itemPrice;

          AXIOS.post('/'.concat(this.$username) + '/shopping-cart/remove-item/'.concat(item.name).concat(item.artist.username))
            .then(response => {
            })
            .catch(error => {
              this.artworkError = error;
            })
        }

      },
      created: function(){
        for(let i = 0; i <this.items.length; i++) {
          this.$totalPrice += this.items[i].itemPrice;
        }
      }
    }
  </script>

  <style scoped>

    .images1{
      width: 100px;
      margin-bottom: 20px;
      border-radius: 15%;
    }

    element.style {
      width: 100px;
    }

    .btn {
      width: 300px;
      height: 60px;
    }

  </style>
