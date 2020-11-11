
<template>
  <body>
    <div class="container">
      <h1 class="heading">Upload Artwork</h1>

      <form class="" action="/compose" method="post">
        <div class="form-group">
          <label>Artwork Name</label>
          <input class="txtbox form-control" type="text" name="postTitle" />

          <label>Description</label>
          <textarea
            class="txtbox form-control"
            name="postContent"
            rows="8"
          ></textarea>

          <div class="row">
            <div class="col">
              <label class="sm">Height:</label>
              <input class="txtbox form-control" type="text" name="postTitle" />
            </div>

            <div class="col">
              <label class="sm">Width:</label>
              <input class="txtbox form-control" type="text" name="postTitle" />
            </div>

            <div class="col">
              <label class="sm">Breadth:</label>
              <input class="txtbox form-control" type="text" name="postTitle" />
            </div>

            <div class="col">
              <label class="sm">Creation Date:</label>
              <input type="Date" class="form-control" />
            </div>
          </div>

          <div class="row">
            <div class="col">
              <label>Price</label>
              <input class="txtbox form-control" type="text" name="postTitle" />
            </div>

            <div class="col">
              <label>Collection Name</label>
              <input class="txtbox form-control" type="text" name="postTitle" />
            </div>
          </div>

          <div class="">
            <label for="exampleInputFile">File input</label>
            <input
              type="file"
              class="form-control-file"
              id="exampleInputFile"
            />
          </div>
        </div>

        <button class="btn btn-dark" type="submit" name="button">
          Publish
        </button>
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
