<script src="./artGallery.js">
</script>

<template>
  <body>
    <div class="container">
      <h1 class="heading">Create Collection</h1>

      <form class="" action="/compose" method="post">
        <div class="form-group">
          <label for="cNamInput">Collection Name</label>
          <input
            v-model="c_name"
            id="cNameInput"
            class="txtbox form-control"
            type="text"
            name="C_name"
          />
          <p>{{ c_name }}</p>

          <label>Collection Description</label>
          <textarea
            v-model="c_description"
            class="txtbox form-control"
            name="postContent"
            rows="8"
          ></textarea>
          <p>{{ c_description }}</p>

          <div class="">
            <label for="exampleInputFile">Upload Collection image <b>(URL Only!)</b></label>
            <input
            v-model="c_name"
            id="exampleInputFile"
            class="txtbox form-control"
            type="text"
            name="exampleInputFile"
            />
          </div>
        </div>

        <router-link to="/collections">
          <button
            @click="uploadCollection"
            class="btn btn-dark"
            type="submit"
            name="button"
          >
            Publish
          </button>
        </router-link>

        <div v-if="collectionError">
          <p>{{ collectionError }}</p>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import AXIOS from "../../App";

export default {
  name: "CreateCollection",

  data() {
    return {
      c_name: "",
      c_description: "",
      c_imageURL: "",
      collection: "",
      collectionError: "",
    };
  },

  methods: {
    uploadCollection: function () {
      /* AXIOS.post('/create-collection/'.concat(this.c_name) + '?collectionDescription' .concat(this.c_description) + '&collectionImageUrl' .concat(this.c_imageURL))
         .then(response => {
          this.collection = response.data;
        })
        .catch(error => {
          this.collectionError = error;
        }) */
    }
  }
};
</script>






<style>
.heading {
  margin-bottom: 20px;
}

.txtbox {
  margin-bottom: 20px;
}
</style>
