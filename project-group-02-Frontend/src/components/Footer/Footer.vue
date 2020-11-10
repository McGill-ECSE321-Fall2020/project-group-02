<template>
<div>

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- displays site properly based on user's device -->
    <link rel="icon" type="image/png" sizes="32x32" href="./images/favicon-32x32.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  </head>

  <body>

    <footer>
      <div class="container">
        <div class="flex align-start">
          <div class="icons">
            <ul>
              <li>
                <a class=" icon-location icons " href="#"> <i class="fas fa-map-marker-alt"></i> </a>
              </li>
              <li>
                <a class="icon-phone icons " href="#"> <i class="fas fa-phone"></i> </a>
              </li>
              <li>
                <a class="icon-email icons " href="#"> <i class="fas fa-envelope-square"></i> </a>
              </li>
            </ul>
          </div>

          <div class="pa">
            <ul>
              <li>Lorem ipsum dolor sit amet, consectetur <br>
                adipiscing elit, sed do eiusmod tempor <br>
                incididunt ut labore et dolore magna aliqua</li>
              <li>+1-543-123-4567</li>
              <li>example@huddle.com</li>
            </ul>
          </div>

          <div class="">
            <ul>
              <li>About Us</li>
              <li>What We Do</li>
              <li>FAQ</li>
            </ul>
          </div>

          <div class="">
            <ul>
              <li>Career</li>
              <li>Blog</li>
              <li>Contact Us</li>
            </ul>
          </div>

          <div class="">

            <ul class="flex">
              <li>
                <a class="circle " href="#"> <i class="fab fa-facebook"></i></a>
              </li>
              <li>
                <a class="circle " href="#"> <i class="fab fa-twitter"></i></a>
              </li>
              <li>
                <a class="circle " href="#"> <i class="fab fa-instagram"></i></a>
              </li>
            </ul>
          </div>

        </div>


        <p>Copyright 2020 KOLIBRI ART. All rights reserved.</p>

      </div>
    </footer>

  </body>
</div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&family=Poppins:wght@600&display=swap');

* {
  box-sizing: border-box;
}

body {
  font-family: 'Open sans', sans-serif;
  font-size: 18px;
  margin: 0;

}

h1,
h2 {
  font-family: 'Poppins', sans-serif;
}

.container {
  width: 1200px;
  max-width: 100%;
  padding: 0 20px;
  margin: 0 auto;
}

p {
  opacity: 0.6;
}

.flex {
  display: flex;
  align-items: center;
}

footer {
  background-color: #14274e;
  color: #fff;
  padding: 20px ;
  margin-bottom: 10px;
}

footer ul {
  list-style-type: none;
}

footer ul li {
  margin-bottom: 20px;
}

.align-start {
  align-items: flex-start;
  margin-top: 30px;
}

.icons {
  color: #fff;
}

.icon-location {
  position: relative;
  top: 5px;
}

.icon-phone {
  position: relative;
  top: 55px;
}

.icon-email {
  position: relative;
  top: 50px;
}

.pa {
  width: 40%;
  margin-left: 0;
}

.circle {
  border: 1px solid #fff;
  border-radius: 50%;
  color: #fff;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  margin-right: 10px;
  width: 40px;
  height: 40px;
  text-decoration: none;
}

.circle:hover {
  color: hsl(322, 100%, 66%);
  border: 1px solid hsl(322, 100%, 66%);
  transition: 1s;
}

footer p {
  text-align: right;
  font-size: 10px;
}

footer p a {
  text-decoration: none;
}


</style>

<script>
</script>
