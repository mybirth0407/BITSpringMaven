<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h1>파일 업로드 예제</h1>
<form method="post" action="upload" enctype="multipart/form-data">
  <label>email:</label>
  <input type="text" name="email" value="mybirth0407@gmail.com">
  <br><br>

  <label>파일1:</label>
  <input type="file" name="file">
  <br>
  <input type="submit" value="upload">
</form>
</body>
</html>
