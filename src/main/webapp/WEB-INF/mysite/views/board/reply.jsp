<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>댓글이다아아강까까아깎</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="content">
    <div id="board">
      <form class="board-form" method="post" action="/board">
        <input type="hidden" name="a" value="reply">
        <input type="hidden" name="no" value="${boardVo.no}">
        <table class="tbl-ex">
          <tr>
            <th colspan="2">답글</th>
          </tr>
          <tr>
            <td class="label">제목</td>
            <td><input type="text" name="title"
                       value="re: ${boardVo.title}"></td>
          </tr>
          <tr>
            <td class="label">내용</td>
            <td>
              <textarea id="content"
                        name="content">${boardVo.content}
--------------------------------------------------------
re: </textarea>
            </td>
          </tr>
        </table>
        <div class="bottom">
          <a href="/board?a=view&no=${boardVo.no}">취소</a>
          <input type="submit" value="등록">
        </div>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp" />
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp" />
</div>
</body>
</html>
