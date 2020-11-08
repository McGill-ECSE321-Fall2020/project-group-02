<template>
 <!-- <div class="card-wrap"
       @mousemove="handleMouseMove"
       @mouseenter="handleMouseEnter"
       @mouseleave="handleMouseLeave"
       ref="card">
    <div class="card"
         :style="cardStyle">
      <div class="card-bg" :style="[cardBgTransform, cardBgImage]"></div>
      <div class="card-info">
        <h1 name="header">{{ collectionName }}</h1>
        <h2 name="content">{{ collectionDescription }}</h2>
        <p name="price">$1,000,000 </p>
      </div>
    </div>
  </div>-->
  <b-card
    :title="itemName"
    img-src="https://picsum.photos/300/300/?image=41"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 15rem; max-height: 30rem; min-height: 30rem;"
    class="mb-2  bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
      <b-card-text>{{ artistName }} </b-card-text>
      <b-card-text>$1,000,000</b-card-text>
      <b-card-footer>
        <b-button bottom :click="addToShoppingCart" class="btn-dark"> Add to my Cart</b-button>
      </b-card-footer>
  </b-card>
</template>

<script>
export default {
  name: "Item",
  props: {
    artistName: {
      type: String,
     // required: true
    },
    itemName: {
      type: String,
      required: true
    },
    itemImageUrl: {
      type: String,
      //required: true
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

.card-wrap {
  margin: 30px;
  cursor: pointer;
  border-radius:30px;
  overflow: hidden;

}
</style>
