<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>꾸아아아아아꺾앙ㄲ</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="content">
    <div id="board">
      <form id="search_form" action="/mysite/board" method="get">
        <input type="text" id="keyword" name="keyword" value="">
        <input type="submit" value="찾기">
      </form>
      <table class="tbl-ex">
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>조회수</th>
          <th>작성일</th>
          <th>&nbsp;</th>
        </tr>
        <c:forEach items="${list}" var="boardVo">
          <tr>
            <td>${boardVo.no}</td>
            <td style="text-align: left;
              padding-left: ${10 * boardVo.depth}px">
                <%--<img src="/assets/images/reply.png">--%>
              <a href="/mysite/board/view/${boardVo.no}">
                  ${boardVo.title}</a></td>
            <td>${boardVo.userName}</td>
            <td>${boardVo.viewCount}</td>
            <td>${boardVo.regDate}</td>
            <c:choose>
              <c:when test="${not empty authUser &&
                              authUser.no == boardVo.userNo}">
                <td><a href="/mysite/board/delete/${boardVo.no}"
                       class="del">삭제</a></td>
              </c:when>
            </c:choose>
          </tr>
        </c:forEach>
      </table>
      <div class="pager">
        <ul>
          <c:if test="${pageMap.left == 1}">
            <li>
              <c:set var="startPage" value="${
              pageMap.startPage - pageMap.N_PAGE}"/>
              <a href="/mysite/board?page=${
              startPage}&keyword=${
              param.keyword}">◀
              </a>
            </li>
          </c:if>
          <c:forEach begin="${pageMap.startPage}"
                     end="${pageMap.lastPage}"
                     var="i">
            <c:choose>
              <c:when test="${i == pageMap.page}">
                <li class="selected">
                  <a href="/mysite/board?page=${
                  i}&keyword=${
                  param.keyword}">${
                    i}
                  </a>
                </li>
              </c:when>
              <c:otherwise>
                <li><a href="/mysite/board?page=${
                i}&keyword=${
                param.keyword}">${
                  i}
                </a>
                </li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${pageMap.right == 1}">
            <c:set var="lastPage" value="${pageMap.lastPage + 1}"/>
            <li>
              <a href="/mysite/board?page=${
              lastPage}&keyword=${
              param.keyword}">▶
              </a>
            </li>
          </c:if>
        </ul>
      </div>
      <c:choose>
        <c:when test="${empty authUser}"></c:when>
        <c:otherwise>
          <div class="bottom">
            <a href="/mysite/board/writeform" id="new-book">글쓰기</a>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
