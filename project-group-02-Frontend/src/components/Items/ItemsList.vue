<template>
  <div class="columns is-centered is-multiline">
    <div class="card column is-one-quarter" v-for="item in items" :key="item.name">
      <Item :name="item.name" @addToShoppingCart="addToShoppingCart"></Item>
      <br>
    </div>
    <div class="section" v-if="items.length === 0">
      <p>{{ noArtworkFound }}</p>
    </div>
    <div class="alert-danger" v-if="artworkError">
      <p> {{ artworkError }} </p>
    </div>
  </div>
</template>

<script>
import Item from "./Item";
import AXIOS from "../../App"

export default {
  name: "ItemsList",
  components: {
    Item
  },
  data() {
    return {
      username: '',
      noArtworkFound: 'No art pieces were found',
      artworkError: '',
      collection: 'The Secrets of the Intelligence',
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
  created: function () {
    /* AXIOS.get('/${collection}')
     .then(response => {
       this.items = response.data;
     })
     .catch(error => {
       this.artworkError = error;
     })*/
  },
  methods: {
    addToShoppingCart: function (artistUsername, itemName) {
    /*  AXIOS.post('/'.concat(username) + '/shopping-cart/add-item/'.concat(itemName).concat(artistUsername))
        .then(response => {
        })
      .catch(error => {
        alert(error);
      })*/
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  margin-top: 20%;
  margin-left: 15%;

  display:flex;
  height:100px;
  flex-wrap:wrap;
}
</style>
