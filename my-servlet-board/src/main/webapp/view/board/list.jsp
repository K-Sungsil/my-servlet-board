<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String[] members = (String[]) session.getAttribute("members");%>
<!DOCTYPE html>
<html lang="en">

<%
  Pagination pagination = (Pagination) request.getAttribute("pagination");
  String type = (String) request.getAttribute("type");
  String keyword = (String) request.getAttribute("keyword");
  String period = (String) request.getAttribute("period");
  String orderBy = (String) request.getAttribute("orderBy");

  String params = "";
  params += "&type=" + type + "&keyword=" + keyword + "&period=" + period + "&orderBy=" + orderBy + "&maxRecordsPerPage=" + pagination.getMaxRecordsPerpage();
  if (keyword != null) {

  } else {
    keyword = "";
  }
%>

<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시판 목록"/>
</jsp:include>
<body>
  <jsp:include page="/view/common/header.jsp">
    <jsp:param name="type" value="<%=type%>"/>
    <jsp:param name="keyword" value="<%=keyword%>"/>
    <jsp:param name="period" value="<%=period%>"/>
    <jsp:param name="orderBy" value="<%=orderBy%>"/>
    <jsp:param name="maxRecordsPerPage" value="<%=pagination.getMaxRecordsPerpage()%>"/>
  </jsp:include>
  <div class="d-flex pt-5 mt-5">
    <div class="flex-fill w-25"></div>
    <h2 class="flex-fill w-50" style="text-align: center;"><b>게시판 목록</b></h2>
    <form class="flex-fill w-25 pr-5 mr-5">
      <input name="type" hidden="hidden" value="<%=type%>">
      <input name="keyword" hidden="hidden" value="<%=keyword%>">
      <input name="period" hidden="hidden" value="<%=period%>">

      <select name="orderBy" onchange="this.form.submit()">
        <option value="latest" <%=orderBy.equals("latest") ? "selected" : ""%>>최신순</option>
        <option value="views" <%=orderBy.equals("views") ? "selected" : ""%>>조회순</option>
        <option value="accuracy" <%=orderBy.equals("accuracy") ? "selected" : ""%>>정확도순</option>
      </select>
      <select name="maxRecordsPerPage" onchange="this.form.submit()">
        <option value="5" <%if(pagination.getMaxRecordsPerpage() == 5 ){%>selected<%}%>>5개씩 보기</option>
        <option value="10" <%if(pagination.getMaxRecordsPerpage() == 10 ){%>selected<%}%>>10개씩 보기</option>
        <option value="15" <%if(pagination.getMaxRecordsPerpage() == 15 ){%>selected<%}%>>15개씩 보기</option>
        <option value="20" <%if(pagination.getMaxRecordsPerpage() == 20 ){%>selected<%}%>>20개씩 보기</option>
        <option value="30" <%if(pagination.getMaxRecordsPerpage() == 30 ){%>selected<%}%>>30개씩 보기</option>
        <option value="40" <%if(pagination.getMaxRecordsPerpage() == 40 ){%>selected<%}%>>40개씩 보기</option>
        <option value="50" <%if(pagination.getMaxRecordsPerpage() == 50 ){%>selected<%}%>>50개씩 보기</option>
      </select>
    </form>
  </div>

  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <% ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
              for (int i = 0; i < boards.size(); i++) { %>

          <tr>
            <th scope="row"><%= boards.get(i).getId() %></th>
            <td><a href="/board/detail?id=<%= boards.get(i).getId()%>"><%= boards.get(i).getTitle() %></a></td>
            <td><%= boards.get(i).getWriter() %></td>
            <td><%= boards.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")) %></td>
            <td><%= boards.get(i).getViewCount() %></td>
            <td><%= boards.get(i).getCommentCount() %></td>
          </tr>
          <%
            }
          %>

        </tbody>
      </table>
      <%
        /*java에서 저장한세션, 세션 읽기 가져오기위해 겟 사용*/
//        String id = (String) session.getAttribute("id");
        if(members != null) /*널이 아니면 로그인됨*/
        {%>
      <div>
        <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
      </div>
      <%}%>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">

          <%
            if (pagination.isHasPrev()) {
          %>
            <li class="page-item">
              <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScrean()-1%><%=params%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%} else {%>
            <li class="page-item disabled">
             <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScrean()-1%><%=params%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%} %>

          <% for (int i = pagination.getStartPageOnScrean(); i <= pagination.getEndPageOnScrean(); i++) {
            if(pagination.getPage() == i ) {
          %>
            <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%} else {%>
            <li class="page-item"><a class="page-link " href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%}}%>

          <%
            if (pagination.isHasNext()) {
          %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScrean()+1%><%=params%>">Next</a>
          </li>
          <%} else {%>
          <li class="page-item disabled" >
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScrean()+1%><%=params%>">Next</a>
          </li>
          <%} %>

        </ul>
      </nav>
    </div>
    </div>
  </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>




</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>