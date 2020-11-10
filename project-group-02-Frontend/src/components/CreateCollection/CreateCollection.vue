<template>
<body>
  <div class="container">

    <h1 class="heading">Create Collection</h1>


    <form class="" action="/compose" method="post">
      <div class="form-group">

        <label>Collection Name</label>
        <input class="txtbox form-control" type="text" name="postTitle">

        <label>Collection Description</label>
        <textarea class="txtbox form-control" name="postContent" rows="8"></textarea>

        <div class= "">
        <label for="exampleInputFile">Upload Collection image</label>
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
