<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<% pageContext.setAttribute("newLine", "\r\n"); %>
<html>
<head>
  <title>my site</title>
  <meta http-equiv="content-type" message="text/html; charset=utf-8">
  <link href="/assets/css/guestbook.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet"
        href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script type="text/javascript"
          src="/assets/js/jquery/jquery-1.9.0.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script type="text/javascript">
    var page = 1;
    var dialogDelete = null;

    var fetchList = function() {
      $.ajax({
        url: "/mysite/guestbook/ajax-list/" + page,
        type: "get",
        dataType: "json",
        data: "",

        success: function(response) {
          if (response.result != "success") {
            return;
          }

          if (response.data.length == 0) {
            console.log("end------------");
            /* $("#btn-next").hide(); 방법1 방명록 다 불러왔으면 버튼 사라지게 함 */
            /* 방법2 */
            alert("다 가져왔어용");
            $("#btn-next").attr("disabled", true);
            return;
          }
          /* HTML 생성한 후에 UL에 append 하는 작업
           * response.data에서 데이터를 하나씩 꺼내서 함수 실행,
           * response.data는 배열상태고 하나씩 꺼내서 vo에 넣는다. */
          $.each(response.data, function(index, guestBookVo) {
//            console.log(index + ":" + guestBookVo);
            $("#gb-list").append(renderHtml(guestBookVo));
          });
          page++;
        },

        error: function(xhr, status, error) {
          console.error(status + ":" + error);
        }
      });
    }

    var renderHtml = function(guestBookVo) {
      /* 대신에 js template 라이브러리 사용 */
      var html =
        "<li id='li-" + guestBookVo.no + "'><table><tr>" +
        "<td>" + guestBookVo.name + "</td>" +
        "<td>" + guestBookVo.regDate + "</td>" +
        "<td><a href='#' class='a-del' data-no= '" + guestBookVo.no +
        "'>삭제</a></td>" +
        "</tr><tr>" +
        "<td colspan='3'>" +
        guestBookVo.message.replace(/\r\n/g, "<br>") +
        "</td></tr></table></li>";
//      $("#gb-list").append(html);
      return html;
    }

    /* 데이터 삽입 */
    $(function() {
      $("#form-insert").submit(function(event) {
        var name = $("#name").val();
        var passwd = $("#passwd").val();
        var message = $("#message").val();

        this.reset();
        event.preventDefault();

        $.ajax({
          url: "/mysite/guestbook/ajax-insert",
          type: "post",
          data: "name=" + name +
          "&passwd=" + passwd +
          "&message=" + message,
          dataType: "json",

          success: function(response) {
            console.log(response.data);
            $("#gb-list").prepend(renderHtml(response.data));
          },

          error: function(xhr, status, error) {
            console.error(status + ":" + error);
          }
        });
      });

      /* 가져오기 버튼 */
      $("#btn-next").on("click", function() {
        fetchList();
      });

      /* 삭제 버튼 */
      $(document).on("click", ".a-del", function(event) {
        event.preventDefault();
//        $("#dialogMessage").dialog();
        var no = $(this).attr("data-no");
        $("#del-no").val(no);
        console.log(no);
        dialogDelete.dialog("open");
      });

      $(window).scroll(function() {
        /* 전체 스크롤 길이 즉, 브라우저 전체 높이*/
        var documentHeight = $(document).height();
        /* 브라우저에 나오는 브라우져의 높이 */
        var windowHeight = $(window).height();
        var scrollTop = $(window).scrollTop();
        console.log(documentHeight + ":" + windowHeight + ":" + scrollTop);
      });

      /* dialogDelete 객체 생성
       *  삭제*/
      dialogDelete = $("#dialog-form").dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
          "삭제": function() {
            var no = $("#del-no").val();
            var delPasswd = $("#del-passwd").val();
            console.log("clicked: " + no + ", " + delPasswd);

            $.ajax({
              url: "/mysite/guestbook/ajax-delete",
              type: "post",
              data: "no=" + no +
              "&passwd=" + delPasswd,

              success: function(response) {
                console.log(response.data);
//                $("#gb-list").(renderHtml(response.data));
//                $("#gb-list").remove();
                $("#li-" + no).remove();
                dialogDelete.dialog("close");
              },

              error: function(xhr, status, error) {
                console.error(status + ":" + error);
              }
            });
          },
          "취소": function() {
            dialogDelete.dialog("close");
          }
        },
        close: function() {
        }
      });

      /* 최초 데이터 가져오기*/
      fetchList();
    });
  </script>
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/mysite/views/include/header.jsp"/>
  <div id="content">
    <div id="guestbook">
      <form id="form-insert">
        <table>
          <tr>
            <td>이름</td>
            <td><input type="text" name="name" id="name"></td>
            <td>비밀번호</td>
            <td><input type="password" name="passwd" id="passwd"></td>
          </tr>
          <tr>
            <td colspan=4><textarea name="message" id="message"></textarea>
            </td>
          </tr>
          <tr>
            <td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
          </tr>
        </table>
      </form>
      <ul id="gb-list"></ul>
      <!-- 편의상 인라인으로 css 만듬 -->
      <div style="margin-top: 20px; text-align:center">
        <button id="btn-next">다음 가져오기</button>
      </div>
    </div>
  </div>
  <c:import url="/WEB-INF/mysite/views/include/navigation.jsp"/>
  <c:import url="/WEB-INF/mysite/views/include/footer.jsp"/>
  <%--<div id="dialogMessage" title="끼야아앆">--%>
  <%--<p style="line-height: 50px">ㅇㅋㅇㅋ</p>--%>
  <%--</div>--%>
  <div id="dialog-form" title="끼야아앆">
    <p class="validateTips">예~</p>
    <form style="margin-top: 20px">
      <label for="passwd">Password</label>
      <input type="hidden" id="del-no" value="">
      <input type="password" name="del-passwd" id="del-passwd" value=""
             class="text ui-widget-content ui-corner-all">
      <input type="submit" tabindex="-1"
             style="position:absolute; top:-1000px">
    </form>
  </div>
</body>
</html>