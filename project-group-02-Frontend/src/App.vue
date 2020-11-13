<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "app",

  created: function () {
    this.AXIOS.post(
      '/art-gallery-system?street=Rue+Crescent&pc=H3G+2A9&province=Quebec&country=Canada&city=Montreal&adminUsername=Ahmad+Siddiqi&adminPassword=abcABC123&adminEmail=ahmad.siddiqi@hotmail.com'
    )
      .then((response) => {
      })
      .catch((error) => {
      });
  },
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
