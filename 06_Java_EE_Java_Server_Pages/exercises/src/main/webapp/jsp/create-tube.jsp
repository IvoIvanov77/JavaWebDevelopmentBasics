<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Tube</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<div class="jumbotron text-center">
    <h1>Create Tube</h1>
    <hr class="my-4">
    <form class="form-group col-md-6 mx-auto" method="post" action="/tubes/create" >
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" required />
        </div>
        <hr class="my-4">
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea  class="form-control" id="description" name="description" ></textarea>
        </div>
        <hr class="my-4">
        <div class="form-group">
            <label for="link">YouTube Link</label>
            <input type="text" class="form-control" id="link" name="youTubeLink" required />
        </div>
        <hr class="my-4">
        <div class="form-group">
            <label for="uploader">Uploader</label>
            <input type="text" class="form-control" id="uploader" name="uploader" required />
        </div>
        <hr class="my-4">
        <button type="submit" class="btn btn-primary">Create Tube</button>
    </form>
    <a href="/">back to home</a>
</div>
</body>
</html>
