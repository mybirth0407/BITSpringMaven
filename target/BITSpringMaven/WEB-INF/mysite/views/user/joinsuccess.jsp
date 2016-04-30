<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
  <title>mysite</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="message">
    <div id="user">
      <p class="jr-success">
        회원가입을 축하합니다.
        <br><br>
        <a href="${pageContext.request.contextPath}loginform">로그인하기</a>
      </p>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
