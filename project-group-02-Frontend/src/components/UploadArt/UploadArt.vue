<template>
<body>
  <div class="container">

    <h1 class="heading">Upload Artwork</h1>


    <form class="" action="/compose" method="post">
      <div class="form-group">

        <label>Artwork Name</label>
        <input class="txtbox form-control" type="text" name="postTitle">

        <label>Description</label>
        <textarea class="txtbox form-control" name="postContent" rows="8"></textarea>

        <div class="row">
          <div class="col">
            <label class="sm">Height:</label>
            <input class="txtbox form-control" type="text" name="postTitle">
          </div>

          <div class="col">
            <label class="sm">Width:</label>
            <input class="txtbox form-control" type="text" name="postTitle">
          </div>

          <div class="col">
            <label class="sm">Breadth:</label>
            <input class="txtbox form-control" type="text" name="postTitle">
          </div>

          <div class="col">
            <label class="sm">Creation Date:</label>
            <input type="Date" class="form-control" />
          </div>
        </div>

        <div class="row">

          <div class="col">
            <label>Price</label>
            <input class="txtbox form-control" type="text" name="postTitle">
          </div>

          <div class="col">
            <label>Collection Name</label>
            <input class="txtbox form-control" type="text" name="postTitle">
          </div>

        </div>

        <div class= "">
        <label for="exampleInputFile">File input</label>
        <input type="file" class="form-control-file" id="exampleInputFile" />
        </div>

      </div>

      <button class=" btn btn-dark" type="submit" name="button">Publish</button>
    </form>
  </div>
</body>
</template>

<style>
.heading {
  margin-bottom: 20px
}

.txtbox {
  margin-bottom: 20px
}
</style>
