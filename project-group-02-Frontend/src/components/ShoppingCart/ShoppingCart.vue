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
            <img class="images1" v-bind:src="item.pathToImage">
            <h4 style="font-size:medium">{{item.name}}</h4>
          </div>
          <div class="col centering">
            <p>{{item.price}} USD</p>
          </div>

          <div class="col centering">

            <div style="display: inline-block" class="qty">1</div>

          </div>
          <div class="col centering">
            <a href="" class="del" v-on:click.prevent="removeFromShoppingCart(item)">Remove</a>
          </div>
          <div class="col centering">
            <div class="totalprice">{{item.price}}</div>
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

        <div>{{ $store.state.totalPrice }}</div>

      </div>

    </div>

    <router-link v-if="!$store.state.user.paymentCredentials.cvc" :to="{name: 'edit'}">

      <button v-if="!$store.state.user.paymentCredentials.cvc" style="margin-top: 50px; margin-bottom: 50px" type="submit" class="btn btn-dark btn-lg">Add Payment Credentials</button>

    </router-link>

   <router-link v-if="$store.state.user.paymentCredentials.cvc" :to="{name: 'checkout'}">

        <button v-if="$store.state.user.paymentCredentials.cvc" style="margin-top: 50px; margin-bottom: 50px" type="submit" class="btn btn-dark btn-lg" :disabled="items.length == 0">Checkout</button>

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
          items: [],
          allUsers: []
        }
      },
      created: function() {
        // Fetch all items in the user's shopping cart
        this.AXIOS.get('/'.concat(this.$store.state.user.username) + '/shopping-cart')
          .then(response => {
            this.items = response.data;

            this.$store.state.totalPrice = 0;
            // Calculate the total price
            for(let item of response.data) {
              this.$store.state.totalPrice += item.price;
            }
          })
          .catch(error => {
            this.artworkError = error;
          })


        // Associate an artist with every item
        this.AXIOS.get('/users')
          .then(response => {
            this.allUsers = response.data;

            for (let item of this.items) {
              for (let user of this.allUsers) {
                if ((user.username + item.name).hashCode() === item.itemId) {
                  item.artist = user;
                }
              }
            }
          })
          .catch(error => {
          });
      },
      methods: {
        removeFromShoppingCart: function (item) {
          let index = this.items.indexOf(item);
          this.items.splice(index, 1);
          this.$store.state.totalPrice -= item.price;

          let i = 35;
          while (i > 0) {
            this.AXIOS.post('/'.concat(this.$store.state.user.username) + '/shopping-cart/remove-item/'.concat(item.name) +'/' .concat(item.artist.username))
              .then(response => {
                console.log("The item was successfully removed from the shopping cart.");
                i = -1;
              })
              .catch(error => {
              })
            i--;
          }

        }
      },
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
