<template>
  <div class="bg-white shadow rounded pb-5 flex flex-col overflow-hidden">
    <div class="block relative h-48 overflow-hidden">
      <img
        :src="null"
        alt="Collection.name"
        class="object-cover object-center w-full h-full block bg-gray-500"
      />
    </div>
    <div class="px-3 flex-1">
      <h2 class="text-gray-900 text-base font-medium my-3 text-capitalize">{{ name }}</h2>
    </div>
    <div class="px-3">
      <b-button class="btn-dark">Visit Collection
      <router-link :to="`/${ name }`" class="button button-orange w-full rounded-full"></router-link>
      </b-button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['name'],
  name: 'Collection'
}
</script>

<style scoped>

</style>
