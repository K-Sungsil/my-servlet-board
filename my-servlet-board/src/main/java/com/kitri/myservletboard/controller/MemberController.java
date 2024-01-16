package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    MemberService memberService = MemberService.getInstance();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다</h1>");

        out.println(request.getRequestURL());

        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String view = "/view/member/";


        if (command.equals("/member/loginForm")) {
            view += "login.jsp";
        } if (command.equals("/member/login")){
            String id = request.getParameter("loginId");
            String password = request.getParameter("password");

            Member member = memberService.getMember(id);

            boolean isLoginFailed = false;
            if (id.isEmpty() || password.isEmpty()) {
                isLoginFailed = true;
            }
            // 멤버스에서 키벨류(겟)로 읽음 , id를 equals 로 쓰면 null체크가 안됨
            if (member == null ) {
                isLoginFailed = true;
                response.sendRedirect("/board/list");
                return;
            } else {
                if(!member.getPassword().equals(password)) {
                    isLoginFailed = true;
                }
            }
            //로그인 실패시 정보를 담음
            if(isLoginFailed){
                request.setAttribute("loginFailed", isLoginFailed);
                response.sendRedirect("/member/loginForm");
                return;
                // 로그인 성공시 세션에 담아줘야한다
            }else { // 자바엔 기본적으로 HttpSession 있음
                // 사용자의 정보를 세션에 저장
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
            }
            response.sendRedirect("/board/list");
            return;
        } else if (command.equals("/member/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();

            response.sendRedirect("/board/list");
            return;

        } else if (command.equals("/member/joinForm")) {
            //회원 가입 폼
            view += "join.jsp";
        } else if (command.equals("/member/join")) {
            //회원 가입 처리
            String name = request.getParameter("name");
            String loginId = request.getParameter("loginId");
            String password = request.getParameter("password");
            String passwordcheck = request.getParameter("passwordcheck");
            String email = request.getParameter("email");

            Member member = new Member(name,loginId, password, passwordcheck, email);
            Member ChkMember = memberService.getMember(loginId);

            // 아이디 중복 체크
            if (ChkMember.getLoginId() != null) {
                //중복
                response.sendRedirect("/view/member/joinFalse.jsp");
                return;
            } else {
                //중복 아니니까
                if (!password.equals(passwordcheck)){
                    //비밀번호 확인 문구
                    response.sendRedirect("/view/member/pwFalse.jsp");
                    return;
                } else {
                    //가입완료
                    memberService.getMember(name, loginId, password, email);
                    response.sendRedirect("/view/member/joinSuccess.jsp");
                    return;
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
