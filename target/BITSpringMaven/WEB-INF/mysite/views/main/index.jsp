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
        <h2>홍대와 건대 사이!</h2>
        <p>
          건대 호수 홍대 놀이터 난 어디든 좋아<br>
          네가 있다면 난 어디든 좋아<br>
          웃는 모습 우는 모습 모두 사랑해 줄게<br>
          네가 있다면 난 어디든 좋아<br><br>
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
