<template>
  <div class="row">
    <div v-for="collection in collections" :key="collection.name" class="column">
        <router-link
          :to="{ name: 'itemsList', params: { collection: collection.name } }"
          class="block no-underline p-3 text-grey-dark  text-capitalize">
          <Collection :name="collection.name"></Collection>
        </router-link>
    </div>
    <div class="section" v-if="collections.length === 0">
      {{ noCollectionFound }}
    </div>
    <div class="alert-danger" v-if="collectionError">
      <p> {{ collectionError }} </p>
    </div>
    </div>
</template>

<script>
import Collection from "./Collection";
import AXIOS from "../../App"

export default {
  name: "CollectionsList",
  components: {
   Collection
  },
  data(){
    return {
      noCollectionFound:'No collections were found',
      collectionError: '',
      collections: [
        {name: "Dogs"},
        {name: "Cats"},
        {name: "Monkeys"}
      ]
    }
  },
  created: function() {
    /*AXIOS.get('/collections', {}, {})
    .then(response => {
      this.collections = response.data;
    })
    .catch(error => {
      console.log(error);
      this.collectionError = error;
    })*/
  },
  methods: {

  },
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 25%;
  padding: 0 10px;
}

/* Remove extra left and right margins, due to padding */
.row {margin: 0 -5px;}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}




</style>
