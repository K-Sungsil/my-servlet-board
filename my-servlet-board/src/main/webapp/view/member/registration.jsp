<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String[] members = (String[]) session.getAttribute("members");%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="회원정보 수정"/>
</jsp:include>
<body>
<jsp:include page="/view/common/header.jsp"/>
    <div class="container">
    <div class="input-form-backgroud row">


        <div class="input-form col-md-12 mx-auto">
            <% if (members != null) { %>
            <h4 class="mb-3"><b>회원 정보 수정</b></h4>
            <hr>
            <br>
            <form class="validation-form" novalidate action="/member/registration">
                <input hidden="hidden" name="id" value="<%=members[0]%>">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name">이름</label>
                        <input name="name" type="text" class="form-control" id="name" placeholder="이름을 입력해주세요" value=""
                            required>
                        <div class="invalid-feedback">
                            이름을 입력해주세요.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="memberId">아이디</label>
                        <input name="memberId" type="text" class="form-control" id="memberId" placeholder="<%=members[1]%>" value="<%=members[1]%>"
                               readonly>
                        <div class="invalid-feedback">
                            아이디를 입력해주세요.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="password">비밀번호</label>
                        <input name="password" type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요"
                            value="" required>
                        <div class="invalid-feedback">
                            비밀번호를 입력해주세요.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="passwordcheck">비밀번호 확인</label>
                        <input name="passwordcheck" type="password" class="form-control" id="passwordcheck" placeholder="비밀번호를 한 번 더 입력해주세요"
                            value="" required>
                        <div class="invalid-feedback">
                            비밀번호를 입력해주세요.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">이메일</label>
                    <input name="email" type="email" class="form-control" id="email" placeholder="Bootstrap@example.com"
                        required>
                    <div class="invalid-feedback">
                        이메일을 입력해주세요.
                    </div>
                </div>

                <hr class="mb-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="aggrement" required>
                    <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <button class="btn btn-secondary btn-block" type="submit">회원 정보 수정</button>
                    </div>
                    <div class="col-md-6 mb-3">
                        <button class="btn btn-secondary btn-block" type="submit">취소</button>
                    </div>
                </div>

        </form>
        <%} else {%>
        <h1><a href="/member/loginForm">로그인</a>이 필요한 페이지 입니다.</h1>
        <% } %>
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