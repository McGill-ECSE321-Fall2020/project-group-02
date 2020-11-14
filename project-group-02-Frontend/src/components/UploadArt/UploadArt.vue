
<template>
  <body>
    <div class="container">
      <h1 class="heading">Upload Artwork</h1>

      <form class="" action="/compose" method="post">
        <div class="form-group">
          <label>Artwork Name</label>
          <input v-model="art_name" class="txtbox form-control" type="text" />

          <label>Description</label>
          <textarea
            v-model="art_description"
            class="txtbox form-control"
            name="postContent"
            rows="8"
          ></textarea>

          <div class="row">
            <div class="col">
              <label class="sm">Height:</label>
              <input
                v-model="art_height"
                class="txtbox form-control"
                type="text"
              />
            </div>

            <div class="col">
              <label class="sm">Width:</label>
              <input
                v-model="art_width"
                class="txtbox form-control"
                type="text"
              />
            </div>

            <div class="col">
              <label class="sm">Breadth:</label>
              <input
                v-model="art_breadth"
                class="txtbox form-control"
                type="text"
              />
            </div>

            <div class="col">
              <label class="sm">Creation Date:</label>
              <input
                v-model="art_creation_date"
                type="Date"
                class="form-control"
              />
            </div>
          </div>

          <div class="row">
            <div class="col">
              <label>Price</label>
              <input
                v-model="art_price"
                class="txtbox form-control"
                type="text"
              />
            </div>

            <div class="col">
              <label>Collection Name</label>
              <input
                v-model="art_collection_name"
                class="txtbox form-control"
                type="text"
              />
            </div>
          </div>

          <div class="">
            <label for="exampleInputFile">Image Url</label>
            <input
              v-model="art_imageURL"
              class="txtbox form-control"
              type="text"
            />
          </div>
        </div>

        <router-link :to="{ name: 'collectionsList' }">
        <button
          @click="uploadArt"
          class="btn btn-dark"
          type="submit"
          name="button"
        >
          Publish
        </button>
        </router-link>
      </form>
    </div>
  </body>
</template>

<style>
.heading {
  margin-bottom: 20px;
}

.txtbox {
  margin-bottom: 20px;
}
</style>

<script>
export default {
  name: "UploadArt",
  data() {
    return {
      artwork: "",
      art_name: "",
      art_description: "",
      art_height: "",
      art_breadth: "",
      art_width: "",
      art_creation_date: "",
      art_price: "",
      art_collection_name: "",
      art_imageURL: ""
    }
  },
  methods: {
    uploadArt: function () {
      this.AXIOS.post(
        "/".concat(this.$store.state.user.username) +
          "/upload-artwork/".concat(this.art_collection_name) +
          "/".concat(this.art_name),
        {},
        {
          params: {
            height: this.art_height,
            width: this.art_width,
            breadth: this.art_breadth,
            creationDate: this.art_creation_date,
            description: this.art_description,
            price: this.art_price,
            imageUrl: this.art_imageURL,
          },
        }
        /*  "?height=".concat(this.art_height) +
          "&width=".concat(this.art_width) +
          "&breadth=".concat(this.art_breadth) +
          "&creationDate=".concat(this.art_creation_date) +
          "&description=".concat(this.art_description) +
          "&price=".concat(this.art_price) +
          "&imageUrl=".concat(this.art_imageURL) */
      )
        .then((response) => {
          this.artwork = response.data;
          console.log(response.data);
        })
        .catch((error) => {});
    },
  },
};
</script>
