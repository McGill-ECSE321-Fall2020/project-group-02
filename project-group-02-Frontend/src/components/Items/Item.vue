<template>
  <b-card
    :title="itemName"
    img-src="https://picsum.photos/300/300/?image=41"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 15rem; width:240px; max-height: 30rem; min-height: 30rem;"
    class="mb-2 bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
    <b-card-body style=" display: flex; flex-direction: column;" class="d-flex flex-column">
      <b-card-text>{{ artistName }}</b-card-text>
      <b-card-text>{{ itemPrice }}</b-card-text>
    </b-card-body>
      <b-button :click="addToShoppingCart" class="shopping-cart-button btn-dark mt-auto"> Add to my Cart</b-button>
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
    }
  },
  methods: {
    addToShoppingCart: function() {
      this.$emit('addToShoppingCart', {artistUsername: this.artistName, itemName: this.itemName});
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
</style>
