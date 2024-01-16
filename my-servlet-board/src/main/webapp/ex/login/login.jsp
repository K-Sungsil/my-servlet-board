<%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-16
  Time: 오전 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 폼</title>
</head>
<body>
<%-- <% %>로 자바 코드 문법넣기   --%>
    <%
        /*java에서 저장한세션, 세션 읽기 가져오기위해 겟 사용*/
        String id = (String) session.getAttribute("id");
        if(id != null) /*널이 아니면 로그인됨*/
        {%>

        <h2>안녕하세요! <%=id%>님 접속중입니다.</h2>
        <a href="/ex/logout">로그아웃</a>

        <%}else {%>

<%--        클라이언트의 요청이 가야하니 form 으로 묶어 주자--%>
    <form method="post" action="/ex/login">
<%--        아이디와 비밀번호 경로--%>
        <label for="id">아이디: </label>
        <input type="text" name="id" id="id"><br>
        <label for="pw">비밀번호: </label>
        <input type="password" name="pw" id="pw"><br>
        <input type="submit" value="로그인">
    </form>
    <div>${requestScope.loginFailed ? "로그인이 실패하였습니다. 아이디 혹은 비밀번호를 정확하게 입력해주세요" : ""}</div>

    <%}%>

</body>
<script>
<%--    자바 스크립트 / 2초후 로그인 실패 문구 사라지도록 할수있다--%>
    setTimeout(() => {
        document.querySelector(".notification").hidden = true;
    }, 2000);
</script>
</html>
