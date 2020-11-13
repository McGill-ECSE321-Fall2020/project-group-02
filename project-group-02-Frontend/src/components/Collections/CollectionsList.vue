<script src="./artGallery.js">
</script>

<template>
  <div class="collections-list">
    <Header></Header>
    <div class="offset-md-2">
      <b-card-group deck>
        <div v-for="collection in collections" :key="collection.name">
          <router-link
            :to="{ name: 'itemsList', params: { collection: collection.name } }"
            class="block no-underline p-3 text-grey-dark text-capitalize"
          >
            <Collection
              :collection-name="collection.name"
              :collection-description="collection.description"
              collection-image-url="https://images.unsplash.com/photo-1479660656269-197ebb83b540?dpr=2&auto=compress,format&fit=crop&w=1199&h=798&q=80&cs=tinysrgb&crop="
            ></Collection>
          </router-link>
        </div>
        <div class="section" v-if="collections.length === 0">
          {{ noCollectionFound }}
        </div>
        <div class="alert-danger" v-if="collectionError">
          <span>{{ collectionError }}</span>
        </div>
      </b-card-group>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Collection from "./Collection";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";

export default {
  name: "CollectionsList",
  components: {
    Header,
    Collection,
    Footer,
  },
  data() {
    return {
      noCollectionFound: "No collections were found",
      collectionError: "",
      collections: [
        { name: "Dogs", description: "Collection description" },
        { name: "Cats", description: "Collection description" },
        { name: "Monkeys", description: "Collection description" },
        { name: "Giraffes", description: "Collection description" },
        { name: "Elephants", description: "Collection description" },
        { name: "Lions", description: "Collection description" },
      ],
    };
  },
  created: function () {
    this.AXIOS.get('/collections')
    .then(response => {
      this.collections = response.data;
    })
    .catch(error => {
      console.log(error);
      this.collectionError = error;
    })
  },
};
</script>

<style scoped>
</style>
