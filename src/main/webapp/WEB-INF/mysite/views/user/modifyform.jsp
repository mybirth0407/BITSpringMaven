<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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
      <form id="join-form" name="joinForm" method="post"
            action="/user">
        <input type="hidden" name="a" value="modify">
        <label class="block-label" for="name">이름</label>
        <input id="name" name="name" type="text"
               value="${userVo.name}">

        <label class="block-label">패스워드</label>
        <input name="passwd" type="password"
               value="${userVo.passwd}">
        <fieldset>
          <legend>성별</legend>
          <c:choose>
          <c:when test="${userVo.gender == f}">
          <label>여</label> <input type="radio" name="gender" value="f"
                                  checked="checked">
          <label>남</label> <input type="radio" name="gender" value="m">
          </c:when>
          <c:otherwise>
          <label>여</label> <input type="radio" name="gender" value="f">
          <label>남</label> <input type="radio" name="gender" value="m"
                                  checked="checked">
          </c:otherwise>
          </c:choose>
        </fieldset>
        <input type="submit" value="수정하기">
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
