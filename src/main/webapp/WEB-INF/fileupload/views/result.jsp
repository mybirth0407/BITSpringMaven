<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h1>Upload completed</h1>

<div class="result-images">
  <c:if test="${not empty productImageURL }">
    <img src="/fileupload${productImageURL }" style="width:150px"><br>
  </c:if>
</div>

<p>
  <a href='/fileupload/form'> 다시 업로드 하기 </a>
</p>

</body>
</html>
