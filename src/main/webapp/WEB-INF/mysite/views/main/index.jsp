<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
  <title>예~</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="${pageContext.request.contextPath}/assets/css/main.css"
        rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="wrapper">
    <div id="message">
      <div id="site-introduction">
        <img id="profile"
             src="https://i.ytimg.com/vi/oa934JAFYB8/hqdefault.jpg">
        <h2>끄아아아까ㅏㅇ깡깎</h2>
        <p>퓨처스<br>
          지옥<br>
          번개<br>
          <a href="/guestbook">방명록</a>에 글 남기기<br>
        </p>
      </div>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
