<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>예에~</title>
  <meta http-equiv="content-type" message="text/html;charset=utf-8">
  <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <div id="header">
    <h1><a href="/main">조졌다아아아앆</a></h1>
    <ul>
      <c:choose>
        <c:when test="${empty authUser}">
          <li><a href="/user?a=loginform">로그인</a></li>
          <li><a href="/user?a=joinform">회원 가입</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="/user?a=modifyform">회원정보 수정</a></li>
          <li><a href="/user?a=logout">로그아웃</a></li>
          <li>${authUser.name} 접속했다!</li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <div id="content">
    <div id="board">
      <form class="board-form" method="post"
            action="/board?a=modify&no=${boardVo.no}">
        <%--<input type="hidden" name="no" value="${boardVo.no}">--%>
        <table class="tbl-ex">
          <tr>
            <th colspan="2">글수정</th>
          </tr>
          <tr>
            <td class="label">제목</td>
            <td><input type="text" name="title"
                       value="${boardVo.title}"></td>
          </tr>
          <tr>
            <td class="label">내용</td>
            <td>
              <textarea id="content"
                        name="content">${boardVo.content}</textarea>
            </td>
          </tr>
        </table>
        <div class="bottom">
          <a href="/board?a=view&no=${boardVo.no}">취소</a>
          <input type="submit" value="수정">
        </div>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp" />
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp" />
</div>
</div>
</body>
</html>
