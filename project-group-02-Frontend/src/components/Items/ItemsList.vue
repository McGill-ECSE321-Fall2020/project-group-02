<template>
  <div>
    <Header></Header>
    <div class="offset-md-2 col-md-8">
      <b-card-group deck class="container">
        <div v-for="item in items" :key="item.name" style="margin:20px;">
          <Item :itemName="item.itemName" :artistName="item.artistName" @addToShoppingCart="addToShoppingCart"></Item>
          <br>
        </div>
        <div class="section" style="margin:auto;" v-if="items.length === 0">
          <p>{{ noArtworkFound }}</p>
        </div>
        <div class="alert-danger" style="margin:auto;" v-if="artworkError">
          <p> {{ artworkError }} </p>
        </div>
      </b-card-group>
    </div>
  </div>
</template>

<script>
import Item from "./Item";
import AXIOS from "../../App"
import Header from "../Header/Header";

export default {
  name: "ItemsList",
  components: {
    Item,
    Header,
  },
  data() {
    return {
      username: '',
      noArtworkFound: 'No art pieces were found',
      artworkError: '',
      collection: 'The Secrets of the Intelligence',
      items: [
        {itemName: "The Peaceful Mind", artistName: "Picasso"},
        {itemName: "My Dear Mother", artistName: "Michelangelo"},
        {itemName: "The Outside", artistName: "Leonardo Da Vinci"},
        {itemName: "Wondering Sherlock Hawkins", artistName: "Michael De Sanjo"},
        {itemName: "The Peaceful Mind", artistName: "Picasso"},
        {itemName: "My Dear Mother", artistName: "Michelangelo"},
        {itemName: "The Outside", artistName: "Leonardo Da Vinci"},
        {itemName: "Wondering Sherlock Hawkins", artistName: "Michael De Sanjo"}
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
  margin-left: 0%;

  display: flex;
  height: 100px;
  flex-wrap: wrap;
}
</style>
