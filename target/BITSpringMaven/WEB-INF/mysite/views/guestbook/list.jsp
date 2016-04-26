<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<% pageContext.setAttribute("newLine", "\r\n"); %>
<html>
<head>
  <title>my site</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="content">
    <div id="guestbook">
      <form action="/mysite/guestbook/insert" method="post">
        <table>
          <tr>
            <td>이름</td>
            <td><input type="text" name="name"></td>
            <td>비밀번호</td>
            <td><input type="password" name="passwd"></td>
          </tr>
          <tr>
            <td colspan=4>
              <textarea name="message" id="message"></textarea>
            </td>
          </tr>
          <tr>
            <td colspan=4 align=right>
              <input type="submit" value=" 확인 "></td>
          </tr>
        </table>
      </form>
      <ul>
        <c:set var="br" value="<br>" scope="page"/>
        <c:set var="count" value="${fn:length(list)}"/>
        <c:forEach items="${list}" var="guestBookVo" varStatus="status">
        <li>
          <table>
            <tr>
              <td>[${count - status.index}]</td>
              <td>${guestBookVo.name}</td>
              <td>${guestBookVo.regDate}</td>
              <td><a href=
                       "/mysite/guestbook/deleteform/${guestBookVo.no}">
                삭제</a></td>
            </tr>
            <tr>
              <td colspan=4>
                <%--${guestBookVo.message}--%>
                ${fn:replace(guestBookVo.message, newLine, br)}
              </td>
            </tr>
          </table>
          <br>
        </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
