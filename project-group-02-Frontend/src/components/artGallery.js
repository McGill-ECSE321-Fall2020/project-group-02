import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function AddressDTO(street, postalCode, province, city, country) {
    this.street = street;
    this.postalCode = postalCode;
    this.province = province;
    this.city = city;
    this.country = country;
}

function ApplicationUserDTO(accountCreationDate, username, email, password, phoneNumber, userRoles, isLoggedIn, ags, bal) {
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

function ItemDTO(itemId, name, height, width, breadth, creationDate, description, price, inStock, artist, collection, artGallerySystem, imageUrl) {

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

export default {
    name: 'artgallery',
    data() {
        return {
            artgallery: '',
            items: [],
            shoppingCart: [],
            collections: [],
            artists: [],
            customers: [],
            error: ''
        }
    },

    created: function () {
        AXIOS.get(`/artgallery`)
        .then(response => {
          this.artgallery = response.data
        })
        .catch(e => {
          this.error = e;
        });
        // TO-DO
    },

    methods: {
       // createPerson: function (personName) {
          // Create a new person and add it to the list of people
        //  var p = new PersonDto(personName)
        //  this.persons.push(p)
          // Reset the name field for new people
       //   this.newPerson = ''
        }
      }
}
