<script src="./artGallery.js">
</script>

<template>
  <div class="collections-list">
    <Header></Header>
    <div class="offset-md-2">
      <b-card-group deck>
        <div v-for="collection in collections" :key="collection.name">
          <router-link
            :to="{ name: 'itemsList', params: { collection: collection.collectionName } }"
            class="block no-underline p-3 text-grey-dark text-capitalize"
          >
            <Collection
              :collectionName="collection.collectionName"
              :collectionDescription="collection.description"
              :collectionImageUrl="collection.pathToImage"
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
      collections: [],
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
  mounted: function () {
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
