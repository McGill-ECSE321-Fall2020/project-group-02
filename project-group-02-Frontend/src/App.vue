<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

import axios from 'axios' 
var config = require('../../config') 
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port 
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort 
var AXIOS = axios.create({
  ​​​​baseURL: backendUrl, 
  headers: {​​​​'Access-Control-Allow-Origin': frontendUrl }​​​​ 
  }​​​​); 
  
</script>

<script>
export default {
  name: 'app'
}
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
