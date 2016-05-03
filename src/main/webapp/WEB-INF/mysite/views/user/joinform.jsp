<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
  <title>예에에에~</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/user.css" rel="stylesheet" type="text/css">
  <script type="text/javascript"
          src="/assets/js/jquery/jquery-1.9.0.js"></script>
  <script type="text/javascript">
    $(function() {
      $("#join-form2").submit(function() {
        /* 이름 유효성 검사 */
        if ($("#name").val() == "") {
          alert("이름은 필수 요소다!");
          $("#name").focus();
          return false;
        }

        /* 이메일 체크 */
        if ($("#email").val() == "") {
          alert("이메일은 필수 요소다!");
          $("#email").focus();
          return false;
        }

        if ($("#img-checkemail").is(":visible") == false) {
          alert("이메일은 중복 체크를 하렴");
          return false;
        }

        /* 패스워드 유효성 체크 */
        /* 약관 체크, 제이쿼리 isChecked */
        alert("제출하셈! 빰");
        return true;
      });

      $("#email").change(function() {
        $("#btn-checkemail").show();
        $("#img-checkemail").hide();
      });

      $("#btn-checkemail").click(function() {
        var email = $("#email").val();
        if (email == "") {
          return;
        }
        console.log(email);
        $.ajax({
          url: "${pageContext.request.contextPath}/mysite/user/checkemail?email=" +
          email, // 요청 URL
          type: "get", // 통신방식 get/post 둘중 하나
          dataType: "json", //  수신 데이터 타입
          data: "", //post방식인 경우 서버에 전달할 파라미터 데이터
          // ex) a=checkemail&email=tyranosaurus@nate.com
          // contentType:"application/json"
          // 보내는 데이터가 JSON형식인 경우,
          // 반드시 post방식인 경우로 보내야함
          // ex)data 부분 :  "{"a":"checkemail", email:afsdf@naver.com"}"
          // 성공시 callback
          success: function(response) {
            console.log(response);
            if (response.result != "success") {
              return;
            }

            if (response.data == false) {
              alert("이미 존재하는 이메일임!");
              $("#email").val("").focus();
              return;
            }

            $("#btn-checkemail").hide();
            $("#img-checkemail").show();
          },
          // 실패시 callback
          error: function(jqXHR, status, error) {
            console.error(status + ":" + error);
          }
        });
      });
    });
  </script>
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="message">
    <div id="user">
      <form id="join-form" name="joinForm" method="post"
            action="/mysite/user/join">
        <label class="block-label" for="name">이름</label>
        <input id="name" name="name" type="text" value="">
        <spring:hasBindErrors name="userVo">
          <c:if test="${errors.hasFieldErrors('name')}">
            <br>
            <strong style="color:orangered">
                ${errors.getFieldError('name').defaultMessage}
            </strong>
          </c:if>
        </spring:hasBindErrors>

        <label class="block-label" for="email">이메일</label>
        <input id="email" name="email" type="text" value="">
        <input id="btn-checkemail" type="button" value="id 중복체크">
        <img id="img-checkemail" style="display:none;"
             src="${pageContext.request.contextPath}/assets/images/check.png">
        <spring:hasBindErrors name="userVo">
          <c:if test="${errors.hasFieldErrors('email')}">
            <br>
            <strong style="color:cadetblue">
                <%--${errors.getFieldError('email')}<br>--%>
              <c:set var="errorName"
                     value="${errors.getFieldError('email').codes[0]}"/>
              <spring:message
                code="${errorName}"
                text="${errors.getFieldError('email').defaultMessage}"/>
            </strong>
          </c:if>
        </spring:hasBindErrors>

        <label class="block-label">패스워드</label>
        <input name="passwd" type="password" value="">

        <fieldset>
          <legend>성별</legend>
          <label>여</label> <input type="radio" name="gender" value="f"
                                  checked="checked">
          <label>남</label> <input type="radio" name="gender" value="m">
        </fieldset>

        <fieldset>
          <legend>약관동의</legend>
          <input id="agree-prov" type="checkbox" name="agreeProv" value="y">
          <label>서비스 약관에 동의합니다.</label>
        </fieldset>

        <input type="submit" value="가입하기">
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
</div>
</body>
</html>
