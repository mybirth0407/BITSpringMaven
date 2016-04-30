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
      <form class="board-form" method="post"
            action="/mysite/board/reply">
        <input type="hidden" name="groupNo" value="${boardVo.groupNo}">
        <input type="hidden" name="orderNo" value="${boardVo.orderNo}">
        <input type="hidden" name="depth" value="${boardVo.depth}">
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
              <textarea id="message"
                        name="content">${boardVo.content}
--------------------------------------------------------
re: </textarea>
            </td>
          </tr>
        </table>
        <div class="bottom">
          <a href="/mysite/board/view/${boardVo.no}">취소</a>
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
