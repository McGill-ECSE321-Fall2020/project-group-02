<template>
  <div style="width: 18rem;" class="card bg-white shadow rounded">
    <div class="card-img-top">
      <img
        src="https://images.unsplash.com/photo-1541680670548-88e8cd23c0f4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
        :alt="collectionName"
        style="width: 12rem; height: 12rem"
        class="object-cover object-center w-full h-full block bg-gray-500"
      />
    </div>
    <div class="card-body">
      <h2 class="text-gray-900 text-capitalize">{{ collectionName }}Collection name</h2>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    collectionName: {
      type: String,
      required: true
    },
    collectionImageUrl: {
      type: String,
      required: true
    }
  },
  collectionName: 'Collection'
}
</script>

<style scoped>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  margin: 65px;
  text-align: center;
  background-color: #f1f1f1;
}
</style>
