<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
`
<%
  pageContext.setAttribute("newLine", "\r\n");
%>
<html>
<head>
  <title>끄꺼어깡까아꺄꺆</title>
  <meta http-equiv="content-type" mssage="text/html; charset=utf-8">
  <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="content">
    <div id="board" class="board-form">
      <table class="tbl-ex">
        <tr>
          <th colspan="2">글보기</th>
        </tr>
        <tr>
          <td class="label">제목</td>
          <td>${boardVo.title}</td>
        </tr>
        <tr>
          <td class="label">내용</td>
          <td>
            <div class="view-message">
              ${fn:replace(boardVo.content, newLine, "<br>")}
            </div>
          </td>
        </tr>
      </table>
      <div class="bottom">
        <a href="/board">글목록</a>
        <c:choose>
          <c:when test="${empty authUser}"></c:when>
          <c:otherwise>
            <a href="/board?a=modifyform&no=${boardVo.no}">글수정</a>
            <a href="/board?a=replyform&no=${boardVo.no}">답글</a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp" />
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp" />
</div>
</body>
</html>
