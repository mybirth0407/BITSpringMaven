<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header">
  <h1><a href="/mysite/main">워우우어 예에~</a></h1>
  <ul>
    <c:choose>
      <c:when test="${empty authUser}">
        <li><a href="${pageContext.request.contextPath}/mysite/user/loginform">
          로그인</a></li>
        <li><a href="${pageContext.request.contextPath}/mysite/user/joinform">
          회원 가입</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="${pageContext.request.contextPath}/mysite/user/modifyform">
          회원정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/mysite/user/logout">
          로그아웃</a></li>
        <li>${authUser.name} 접속했다!</li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>