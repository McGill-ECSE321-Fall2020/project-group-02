<template>
  <div class="bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
    <div class="block relative h-48 overflow-hidden">
     <img
        :src="null"
        alt="item.name"
        class="object-cover object-center w-full h-full block bg-gray-500"
      />
    </div>
    <div class="px-3 flex-1">
      <h2 class="text-gray-900 text-base font-medium my-3 text-capitalize">{{ name }} Item Title</h2>
      <p class="mb-3 text-lg font-bold text-orange">$1,000,000</p>
    </div>
    <div class="px-3">
      <b-button class="btn-dark"> Add to my Cart</b-button>
      <router-link :to="`/item/${name}`" class="button button-orange w-full rounded-full"></router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "Item",
  props: ['name']
}
</script>

<style scoped>

</style>
