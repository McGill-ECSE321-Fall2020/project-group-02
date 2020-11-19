<template>
  <div>
    <div>
    </div>
    <div style="position: relative;">
      <Header></Header>
      <div class="offset-md-7 viewport">
        <div class="viewport__body">
          <div class="btn-container">
            <div class="btn-container__search ">
              <span>Search</span>
              <input type="text" v-model="artistSearched" name="search-query">
              <a @click="searchItemsByArtist" class="btn-search">
                <svg width="100%" x="0px" y="0px" viewBox="336 204.5 318 323" enable-background="new 336 204.5 318 323"
                     xml:space="preserve">
                <g>
                <path class="icon-search" id="path12423" fill="#FFFFFF" d="M386.9,256.6c-43.3,43.3-43.4,114.2-0.1,157.4c36.7,36.7,93.2,42.2,135.9,16.8l76.3,74.2
      c10.5,10.2,27.1,9.8,37.1-0.8c10-10.7,9.8-27.4-0.7-37.6l-75.2-72.9c26.4-42.8,21-100-16-137.1C501,213.3,430.2,213.3,386.9,256.6
      L386.9,256.6z M418.7,288.3c26.1-26.1,67.7-26.1,93.8,0c26.1,26.1,26.1,67.7,0,93.8c-26.1,26.1-67.7,26.1-93.8,0
      C392.6,356.1,392.6,314.4,418.7,288.3z"/>
              </g>
            </svg>
              </a>
            </div>
            <div class="btn-container__filter">
              <div class="filter-label"><span class="filter-text">Sort by...</span></div>
              <span>Sort</span>
              <div class="filter-button">
                <svg class="filter-arrow" width="245px" height="154px" viewBox="0 0 245 154">
                  <g id="arrow" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                    <g id="-" transform="translate(-1.000000, 0.000000)" fill="#D8D8D8">
                      <path d="M1,-0.311842754 L246,-0.311842754 L123.500004,153.654906 L1,-0.311842754 Z"
                            class="the-arrow" id="arrow-1" sketch:type="MSShapeGroup"></path>
                    </g>
                  </g>
                </svg>
              </div>
              <ul class="filter-dropdown">
                <li><a href="" v-on:click.prevent="sortItemsByPriceDescending">Price Descending</a></li>
                <li><a href="" v-on:click.prevent="sortItemsByPriceAscending">Price Ascending</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div class="offset-md-2 col-md-8">
        <b-card-group deck class="container">
          <div v-for="item in items" :key="item.name" style="margin:20px;">
            <Item :itemName="item.name" :artistName="item.artist.username" :itemImageUrl="item.pathToImage"
                  :itemPrice="item.price" @removefromshoppingcart="(artistUsername, itemName)=>this.removeFromShoppingCart(artistUsername, itemName)"
                  @addtoshoppingcart="(artistUsername, itemName)=>this.addToShoppingCart(artistUsername, itemName)"
            @deleteitem="(artistUsername, itemName)=>this.deleteItem(artistUsername, itemName)"></Item>
            <br>
          </div>
          <div class="section" style="margin:auto;" v-if="items.length === 0">
            <p>{{ noArtworkFound }}</p>
          </div>
          <div class="alert-danger" style="margin:auto;" v-if="artworkError">
            <span> {{ artworkError }} </span>
          </div>
        </b-card-group>
      </div>
    </div>
  </div>

</template>


<script>
import Item from "./Item";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";

export default {
  name: "ItemsList",
  components: {
    Item,
    Header,
    Footer
  },
  data() {
    return {
      noArtworkFound: 'No art pieces were found',
      artworkError: '',
      collection: '',
      items: [],
      artistSearched: ''
    }
  },
  created: function () {
    this.collection = this.$route.params.collection;
    this.AXIOS.get('/'.concat(this.collection))
      .then(response => {
        this.items = response.data;
      })
      .catch(error => {
        this.artworkError = error;
      })

    // Find the artists for each item, because item doesn't have a direct associate with a user.
    // It only contains the username of the artist in the itemId encoded as a hashcode.
    // Here we find the users and try to match the username in the itemId with the username of the found
    // user which corresponds to the artist.

    let allUsers = [];

    this.AXIOS.get('/users')
    .then(response => {
      allUsers = response.data;

      for (let item of this.items) {
        for (let user of allUsers) {
          if ((user.username + item.name).hashCode() === item.itemId) {
            item.artist = user;
          }
        }
      }
    })
    .catch(error => {
     // alert('There was an error fetching the items on our part. Try to reload the page.');
    });
  },
  beforeMount: function () {
    this.collection = this.$route.params.collection;
    this.AXIOS.get('/'.concat(this.collection))
      .then(response => {
        this.items = response.data;
      })
      .catch(error => {
        this.artworkError = error;
      })


    // Find the artists for each item, because item doesn't have a direct associate with a user.
    // It only contains the username of the artist in the itemId encoded as a hashcode.
    // Here we find the users and try to match the username in the itemId with the username of the found
    // user which corresponds to the artist.

    let allUsers = [];

    this.AXIOS.get('/users')
      .then(response => {
        allUsers = response.data;

        for (let item of this.items) {
          for (let user of allUsers) {
            if ((user.username + item.name).hashCode() === item.itemId) {
              item.artist = user;
            }
          }
        }
      })
      .catch(error => {
      //  alert('There was an error fetching the items on our part. Try to reload the page.');
      });
  },
  methods: {
    searchItemsByArtist: function () {
      this.AXIOS.get('/'.concat(this.collection) + '/'.concat(this.artistSearched))
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          this.artworkError = error;
        })
    },
    sortItemsByPriceAscending: function () {
      this.AXIOS.get('/'.concat(this.collection) + '/sort-by-price-asc')
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          this.artworkError = error;
        })
    },
    sortItemsByPriceDescending: function () {
      this.AXIOS.get('/'.concat(this.collection) + '/sort-by-price-desc')
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          this.artworkError = error;
        })
    },
    addToShoppingCart: function (artistUsername, itemName) {
      this.AXIOS.post('/'.concat(this.this.$store.state.user.username) + '/shopping-cart/add-item/'.concat(itemName).concat(artistUsername))
        .then(response => {
        })
        .catch(error => {
          this.artworkError = error;
        })
    },
    removeFromShoppingCart: function (artistUsername, itemName) {
      this.AXIOS.post('/'.concat(this.this.$store.state.user.username) + '/shopping-cart/remove-item/'.concat(itemName).concat(artistUsername))
        .then(response => {
        })
        .catch(error => {
          this.artworkError = error;
        })
    },
    deleteItem: function (artistUsername, itemName) {
      this.AXIOS.post('/'.concat(this.$store.state.user.username) + '/delete-item-from-gallery/'.concat(itemName) + '/' .concat(artistUsername));
    }
  }
}
</script>


<style scoped lang="scss">
.container {
  width: 100%;
  margin-left: 0;

  display: flex;
  height: 100px;
  flex-wrap: wrap;
}

// Note: Swap to 3d transforms for hardware acceleration & improved performance
// Setup some variables
$speed: '0.5s';
$hard-transition: all #{$speed} cubic-bezier(0, 0, .10, 1) 0s;
$soft-transition: all #{$speed} cubic-bezier(0, 0, .50, 1) 0s;
$button-height: 3rem;

// Some defaults & resets

a {
  color: #fff;
  text-decoration: none;
}

p {
  padding: 1rem;
  text-align: left;

  a {
    color: #000;
    border-bottom: 1px dashed black;
  }
}

body,
html {
  font-size: 18px;
  line-height: 1.5rem;
  font-family: Helvetica, Arial;
  font-weight: lighter;
  -webkit-font-smoothing: antialiased;
}

// Just to emphasize a viewport

.viewport {
  width: 320px;
  height: 200px;
  transform: translateY(25%);
}

.btn-container {
  position: relative;
  background-color: #000;
  border: 1px solid #efefef;
  margin-right: 10%;
  width: 90%;
  height: $button-height;

  > div {
    margin: 0;
    padding: 0;
    // Shared styling for both buttons.

    position: absolute;
    width: 50%;
    background-color: #000;
    color: #fff;
    text-align: center;

    // Styling for button labels. They are the same, so the default and hover states live here.

    span {
      line-height: $button-height;
      transition: $soft-transition;
      transition-delay: 1s;
    }

    // Hover States for both buttons.

    &:hover {
      background-color: #333;

      span {
        opacity: 0;
        transition-delay: 0s;
      }
    }

    &:first-child {

      // Adds a separator after the first button. Animation settings for this are in the search class.

      &:after {
        content: "";
        width: 1px;
        background-color: #555;
        height: 70%;
        position: absolute;
        right: 0;
        top: 15%;
        transition: $hard-transition;
      }

      span {
        transition-delay: 0.25s;
      }

    }

  }

  // Search Button Interaction

  &__search {
    // Default state styling

    overflow: hidden;
    transition: $hard-transition;
    transition-delay: 0.2s;
    z-index: 1;

    input {
      position: absolute;
      font-size: 18px;
      top: 0;
      height: $button-height;
      border: none;
      box-sizing: border-box;
      z-index: 2;
      width: 80%;
      transform: translateX(-190%);
      transition: transform 0s linear 0s;
      transition-delay: 0s;
    }

    svg {
      height: $button-height;
      position: absolute;
      top: 0;
      left: 0;
    }

    .field-circle {
      transform: translateY(0) translateX(-100px) scale(0);
      transition: $hard-transition;
      transition-delay: 0.2s;
    }

    .btn-search {
      display: block;
      position: absolute;
      right: 0;
      top: $button-height; // Hide the button

      width: 20%;
      height: 100%;
      line-height: $button-height; // Ensure the button is the height of the button

      transition: $hard-transition;
      transition-delay: 0s;
      cursor: pointer;
    }

    .icon-search {
      width: 50%;
      transform: scale(.5) translateX(165%) translateY(120%);
    }

    // Search Button Hover State

    &:hover {

      // Widen the Search button

      width: 100%;
      z-index: 1;
      transition-delay: 0s;

      // Fade out the "Search" label

      span {
        opacity: 0;
        transition-delay: 0s;
      }

      // Bring in the circle to fill in the input field background

      .field-circle {
        transform: translateY(-50%) translateX(10px) scale(2.5);
        transition-delay: 0s;
      }

      // Bring in the Search Icon

      .btn-search {
        top: 0;
        transition-delay: 0.3s;
      }

      // Fade out the small separator

      &:after {
        opacity: 0;
      }

      // Animate the input box

      input {
        transform: translateX(-75%);
        transition: transform 0s linear 1s;
      }
    }

  }

  // Filter Button Interaction

  &__filter {
    left: 50%;
    transition: $hard-transition;
    transition-delay: 1s;

    .filter-button {
      position: absolute;
      right: 0;
      top: 0;
      width: 20%;
      height: 100%;
      overflow: hidden;
      cursor: pointer;
    }

    // Positoning for the arrow svg

    svg {
      position: absolute;
      right: 0;
      top: 0;
      width: 20px;

      transform: translateX(-90%);
      transition: $hard-transition;
      transition-delay: #{$speed};

      .the-arrow {
        transform-origin: center center;
        transition: all #{$speed} cubic-bezier(0, 0, .50, 1) 0s;
      }

    }

    // The actual box for the dropdown selector.

    .filter-dropdown {
      overflow: hidden; // Hide the text!
      position: absolute;
      left: 0;
      width: 0;
      height: 0;

      border-top: 3px solid #ddd;
      transform: translateY(0);

      li {
        background-color: #dedede;

        a {
          display: block;
          padding: 1rem;
          font-size: 0.8rem;
          width: 100%;
          color: black;
          box-sizing: border-box;
        }

        &:nth-child(odd) {
          background-color: #efefef;
        }
      }
    }

    // Filter Label is the dropdown's default "selection" area.

    .filter-label {

      overflow: hidden;
      position: absolute;
      top: 0;
      left: 0;
      width: 0;
      height: $button-height;

      background-color: #ccc;
      transition: $hard-transition;
      transition-delay: #{$speed};

      // Styling for the text inside the dropdown label

      span {
        color: #666;
        opacity: 0;
        transition: $hard-transition;
        transition-delay: 0s;
      }
    }

    // Filter Button Hover Changes

    &:hover {

      // Expand the Filter Button

      width: 100%;
      left: 0;
      z-index: 1;
      transition-delay: 0s;

      // Animate the Arrow

      svg {
        transform: translateY(-48px) translateX(-90%);

        .the-arrow {
          transform: rotate(180deg);
          transition: #{$speed} cubic-bezier(0, 0, .50, 1) 1.35s;
        }
      }

      // Bring in the Label

      .filter-label {
        width: 80%;
        transition-delay: 0.25s;

        span {
          opacity: 1;
          transition-delay: 0.5s;
        }
      }

      // Pop out the dropdown menu

      .filter-dropdown {
        animation: filter-open #{$speed} 1s ease 1 forwards;
      }

    }

  }
}

@keyframes filter-open {
  0% {
    width: 0;
    height: 3px;
  }

  40% {
    width: 90%;
    height: 3px;
  }

  50% {
    width: 100%;
    height: 3px;
  }

  51% {
    width: 100%;
    border-top: 0;
  }

  90% {
    width: 100%;
    height: 285px;
    border-top: 0;
    // Needs to be revisited
  }

  100% {
    width: 100%;
    height: auto;
    border-top: 0;
  }


}

#footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 300px; /* Height of the footer */
}
</style>
