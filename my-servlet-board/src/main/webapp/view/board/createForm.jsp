<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String[] members = (String[]) session.getAttribute("members");%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="등록하기"/>
</jsp:include>
<body>
<jsp:include page="/view/common/header.jsp"/>
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3"><b>게시물 등록</b></h4>
        <hr>
        <br>
        <form class="validation-form" novalidate action="/board/create" method="post">
          <input hidden="hidden" name="memberId" value="<%=members[0]%>">
          <div class="mb-3">
            <label for="title">제목</label>
            <input name="title" type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" required>
            <div class="invalid-feedback">
              제목을 입력해주세요.
            </div>
          </div>

          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="name">작성자</label>
              <input name="writer" type="text" class="form-control" id="name" placeholder="<%=members[3]%>" value="<%=members[3]%>" readonly>
              <div class="invalid-feedback">
                작성자를 입력해주세요.
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea name="content" id="content" class="form-control" cols="30" rows="5" placeholder="내용을 입력해주세요"></textarea>
          </div>
          <br>
          <div class="row">
            <div class="col-md-6 mb-3">
              <button class="btn btn-secondary btn-block" type="submit">게시물 등록하기</button>
            </div>
            <div class="col-md-6 mb-3">
              <button class="btn btn-secondary btn-block" type="submit">취소</button>
            </div>
          </div>


      </form>
    </div>
  </div>
  <div class="p-2">
    <div class="footer">
      <footer>
        <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
      </footer>
    </div>
  </div>
  </div>
  <script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>