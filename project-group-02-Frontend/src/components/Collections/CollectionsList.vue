<template>
  <div>
    <Header></Header>
    <b-card-group deck class="container row">
      <div v-for="collection in collections" :key="collection.name">
        <router-link
          :to="{ name: 'itemsList', params: { collection: collection.name } }"
          class="block no-underline p-3 text-grey-dark  text-capitalize">
          <Collection :collection-name="collection.name" :collection-description="collection.description" collection-image-url="https://images.unsplash.com/photo-1479660656269-197ebb83b540?dpr=2&auto=compress,format&fit=crop&w=1199&h=798&q=80&cs=tinysrgb&crop="></Collection>
        </router-link>
      </div>
      <div class="section" v-if="collections.length === 0">
        {{ noCollectionFound }}
      </div>
      <div class="alert-danger" v-if="collectionError">
        <p> {{ collectionError }} </p>
      </div>
    </b-card-group>
  </div>

</template>

<script>
import Collection from "./Collection";
import AXIOS from "../../App"
import Header from "../Header/Header";

export default {
  name: "CollectionsList",
  components: {
    Header,
   Collection
  },
  data(){
    return {
      noCollectionFound:'No collections were found',
      collectionError: '',
      collections: [
        {name: "Dogs", description: "Collection description"},
        {name: "Cats", description: "Collection description"},
        {name: "Monkeys", description: "Collection description"},
        {name: "Giraffes", description: "Collection description"},
        {name: "Elephants", description: "Collection description"},
        {name: "Lions", description: "Collection description"}

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


body {
  margin: 40px 0;
  font-family: "Raleway";
  font-size: 14px;
  font-weight: 500;
  background-color: #BCAAA4;
  -webkit-font-smoothing: antialiased;
}

.container {
  width: 100%;
  margin-top: 0%;
  margin-left: 15%;

  display:flex;
  height:100px;
  flex-wrap:wrap;
}
</style>
