
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
            <label for="exampleInputFile">File input</label>
            <input
              v-model="art_imageURL"
              class="txtbox form-control"
              type="text"
            />
            <input
              type="file"
              class="form-control-file"
              id="exampleInputFile"
            />
          </div>
        </div>
        <router-link :to="{ name: 'collectionsList'}">
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
      art_imageURL:""
    };
  },
  methods: {
    uploadArt: function () {
      this.AXIOS.post(
        "/" +
          this.$username +
          "/upload-artwork/".concat(this.art_collection_name) +
          "/".concat(this.art_name) +
          "?height=".concat(this.art_height) +
          "&width=".concat(this.art_width) +
          "&breadth=".concat(this.art_breadth) +
          "&creationDate=".concat(this.art_creation_date) +
          "&description=".concat(this.art_description) +
          "&price=".concat(this.art_price) +
          "&imageURL=".concat(this.art_imageURL)
      )
        .then((response) => {
          this.artwork = response.data;
        })
        .catch((error) => {});
    },
  },
};
</script>









<script>
function AddressDTO(street, postalCode, province, city, country) {
  this.street = street;
  this.postalCode = postalCode;
  this.province = province;
  this.city = city;
  this.country = country;
}

function ApplicationUserDTO(
  accountCreationDate,
  username,
  email,
  password,
  phoneNumber,
  userRoles,
  isLoggedIn,
  ags,
  bal
) {
  this.accountCreationDate = accountCreationDate;
  this.username = username;
  this.email = email;
  this.password = password;
  this.phoneNumber = phoneNumber;
  this.artGallerySystem = ags;
  this.userRoles = ur;
  this.isLoggedIn = isLoggedIn;
  this.balance = bal;
}

function ArtGallerySystemDTO(users, totalProfit, artGalleryId, address, items) {
  this.applicationUsers = users;
  this.totalProfit = totalProfit;
  this.artGalleryId = artGalleryId;
  this.address = address;
  this.items = items;
}

function ArtistDTO(items) {
  this.items = items;
}

function CollectionDTO(name, description, pathToImage, items) {
  this.collectionName = name;
  this.description = description;
  this.pathToImage = pathToImage;
  this.items = items;
}

function CustomerDTO(paymentCredentials, shoppingCart, itemOrders) {
  this.paymentCredentials = paymentCredentials;
  this.shoppingCart = shoppingCart;
  this.itemOrders = itemOrders;
}

function ItemDTO(
  itemId,
  name,
  height,
  width,
  breadth,
  creationDate,
  description,
  price,
  inStock,
  artist,
  collection,
  artGallerySystem,
  imageUrl
) {
  this.itemId = itemId;
  this.name = name;
  this.height = height;
  this.width = width;
  this.breadth = breadth;
  this.creationDate = creationDate;
  this.description = description;
  this.price = price;
  this.inStock = inStock;
  this.artist = artist;
  this.collection = collection;
  this.artGallerySystem = artGallerySystem;
  this.pathToImage = imageUrl;
}

function ItemOrderDTO(itemOrderId, itemOrderDate, customer, delivery, items) {
  this.itemOrderId = itemOrderId;
  this.itemOrderDate = itemOrderDate;
  this.customer = customer;
  this.delivery = delivery;
  this.items = items;
}

function PaymentCredentialsDTO(cardHolderName, ccNumber, expirationDate, cvc) {
  this.cardHolderName = cardHolderName;
  this.ccNumber = ccNumber;
  this.expirationDate = expirationDate;
  this.cvc = cvc;
}

function ShoppingCartDTO(shoppingCartId, items) {
  this.shoppingCartId = shoppingCartId;
  this.items = items;
}

function UserRoleDTO(id) {
  this.userRoleId = id;
}
</script>
