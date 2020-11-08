<template>
  <div class="columns is-centered is-multiline inline-block">
    <div v-for="collection in collections" :key="collection.name" class="inline-block card column is-one-quarter">
      <router-link
        :to="{ name:'collection', params: { collection: collection.name } }"
        class="block no-underline p-3 text-grey-dark  text-capitalize">
      </router-link>
      <Collection :name="collection.name"></Collection>

    </div>
    <div class="section" v-if="collections.length === 0">
      {{ noCollectionLabel }}
    </div>
    </div>
</template>

<script>
import Collection from "./Collection";
export default {
  name: "CollectionsList",
  components: {
   Collection
  },
  data(){
    return {
      noCollectionLabel: 'No Collections Found',
      collections: [
        {name: "Dogs"},
        {name: "Cats"},
        {name: "Monkeys"}
      ]
    }
  }
}
</script>

<style scoped>

</style>
