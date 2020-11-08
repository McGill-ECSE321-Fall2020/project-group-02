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
  <div class="bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
    <div class="block relative h-48 overflow-hidden">
     <img
        :src="null"
        :alt="name"
        class="object-cover object-center w-full h-full block bg-gray-500"
      />
    </div>
    <div class="px-3 flex-1">
      <h2 class="text-gray-900 text-base font-medium my-3 text-capitalize">{{ itemName }} Item Title</h2>
      <h3 class="text-gray-900 text-white font-small my-3">{{ artistName }}</h3>
      <p class="mb-3 text-lg font-bold text-orange">$1,000,000</p>
    </div>
    <div class="px-3">
      <b-button :click="addToShoppingCart" class="btn-dark"> Add to my Cart</b-button>
    </div>
  </div>
</template>

<script>
export default {
  itemName: "Item",
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
    collection: {
      type: String,
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

<style scoped>

</style>
