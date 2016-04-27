<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
  <title>mysite</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="message">
    <div id="guestbook" class="delete-form">
      <form method="post" action="/mysite/guestbook/delete">
        <input type="hidden" name="no" value=${no}>
        <label>비밀번호</label>
        <input type="password" name="passwd">
        <input type="submit" value="확인">
      </form>
      <a href="${pageContext.request.contextPath}/mysite/guestbook">
        방명록 리스트</a>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
