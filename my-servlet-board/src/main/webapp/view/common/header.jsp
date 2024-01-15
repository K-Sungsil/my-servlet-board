<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="d-flex flex-row">
    <a class="logo" href="/board/list">
        <span class="material-symbols-outlined">clear_night</span></a>
    <nav class="flex-fill">
        <ul class="nav-items d-flex flex-row">
            <li><a href="/board/list">게시글목록</a></li>
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>

            <div class="flex-fill"></div>
            <form class="d-flex pt-2 pb-3 " role="search" action="/board/list">

                <select class="me-2" name="period">
                    <option value="100 year" ${param.period == "100 year" ? "selected" : ""}>전체기간</option>
                    <option value="1 day" ${param.period== "1 day" ? "selected" : ""}>1일</option>
                    <option value="1 week" ${param.period == "1 week" ? "selected" : ""}>1주일</option>
                    <option value="1 month" ${param.period == "1 month" ? "selected" : ""}>1개월</option>
                    <option value="6 month" ${param.period == "6 month" ? "selected" : ""}>6개월</option>
                    <option value="1 year" ${param.period == "1 year" ? "selected" : ""}>1년</option>
                </select>
                <select class="me-1" name="type">
                    <option value="title" ${param.type == "title" ? "selected" : ""}>제목</option>
                    <option value="writer" ${param.type == "writer" ? "selected" : ""}>작성자</option>
                </select>
                &nbsp;
                <input name="keyword" class="form-control me-2" type="search" placeholder="Search" aria-label="Search" value="${param.keyword}">
                <button class="btn btn-outline-dark" type="submit">Search</button>
            </form>
        </ul>
    </nav>
</header>
