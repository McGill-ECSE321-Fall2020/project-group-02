<template>
  <div class="columns is-centered is-multiline">
    <div class="card column is-one-quarter" v-for="item in items" :key="item.name">
      <Item :name="item.name"></Item>
      <br>
    </div>
    <div class="section" v-if="items.length === 0">
      <p>{{ noArtworkLabel }}</p>
    </div>
  </div>
</template>

<script>
import Item from "./Item";

export default {
  name: "ItemsList",
  components: {
    Item
  },
  data() {
    return{
      noArtworkLabel: 'No Artwork Found',
      items: [
        {name: "The Peaceful Mind"},
        {name: "My Dear Mother"},
        {name: "The Outside"},
        {name: "Wondering Sherlock Hawkins"}
      ],
      filteredItems: [],
      sortedItems: []
    }
  },
  methods: {
  }
}
</script>

</script>

<style scoped>

</style>
