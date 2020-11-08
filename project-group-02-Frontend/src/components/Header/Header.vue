<template>
      <div class="topnav">
  <a class="active" href="#home">Home</a>
  <a href="#Profile">Profile</a>
  <input type="button" value="Logout">
  <a href="#about">About</a>
</div> 
</template>
<script>
</script>
<style>
.topnav {
  background-color: #16181a;
  overflow: hidden;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 16px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}

.topnav input {
  float: left;
  background: #00000000;
  text-align: center;
  padding: 16px 16px;
  text-decoration: none;
  color: white;
  font-size: 17px;
  border: none;
}

.topnav input:hover {
  background-color: #ddd;
  color: black;
}

.topnav input.active {
  background-color: #4CAF50;
  color: white;
}
</style>