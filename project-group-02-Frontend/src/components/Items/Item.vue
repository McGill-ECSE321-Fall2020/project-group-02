<script src="./artGallery.js">
</script>

<template>
  <b-card
    tag="article"
    style="max-width: 15rem; width:240px; max-height: 30rem; min-height: 30rem;"
    class="mb-2 bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
    <img class="card-img-top" :src="itemImageUrl" :alt="itemName">
    <br>
    <br>
    <h5 class="card-title">{{itemName}}</h5>
    <b-card-body style=" display: flex; flex-direction: column;" class="d-flex flex-column">
      <b-card-text>{{ artistName }}</b-card-text>
      <b-card-text>${{ itemPrice }} USD</b-card-text>
      <b-button v-if="!addedToCart" @click="addToShoppingCart" class="shopping-cart-button btn-dark mt-auto"> Add to My Cart</b-button>
      <b-button v-if="addedToCart" @click="removeFromShoppingCart" class="shopping-cart-button btn-danger mt-auto"> Remove From Cart</b-button>
    </b-card-body>
  </b-card>
</template>

<script>

export default {
  name: "Item",
  props: {
    artistName: {
      type: String,
      required: true
    },
    itemName: {
      type: String,
      required: true
    },
    itemImageUrl: {
      type: String,
      required: true
    },
    itemPrice: {
      type: Number,
      required: true
    },
  },
  data() {
    return {
      addedToCart: false
    };
  },
  methods: {
    addToShoppingCart: function() {
      this.addedToCart = true;
      this.$emit('addtoshoppingcart', {artistUsername: this.artistName, itemName: this.itemName});
    },
    removeFromShoppingCart: function() {
      this.addedToCart = false;
      this.$emit('removefromshoppingcart', {artistUsername: this.artistName, itemName: this.itemName});
    }
  }
}
</script>

<style scoped lang="scss" >
.shopping-cart-button {
  font-size: 0.9em;
  background: white;
  width: 100%;
  color: #a7a7a7;
  display: inline-block;
  margin-top: 2em;
  border-radius: 5em;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 0.15em;
  box-shadow: 0 15px 20px -10px rgba(0, 0, 0, 0.3);
  transition: color 0.6s, box-shadow 0.3s, transform 0.3s;
  &:hover {
    box-shadow: 0 3px 5px -5px rgba(0, 0, 0, 0.3);
    transform: translateY(-1px);
    color: #6f6f6f;
  }
}

.card-img-top {
  width: 100%;
  height: 12vw;
  object-fit: cover;
}
</style>
