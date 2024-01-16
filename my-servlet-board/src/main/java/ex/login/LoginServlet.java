package ex.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ex/login") // 절대 경로
public class LoginServlet extends HttpServlet {
    // 서블릿 선언 extends HttpServlet

    // 어떠한 경로로 응답요청 알트+인트 겟과 포스트

    static HashMap<String, String> members = new HashMap<>();
    static {
        members.put("abc123", "12345");
        members.put("captain91", "12345");
        members.put("ilovecoding", "12345");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자가 로그인.jsp 요청, 겟일땐 실행
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/login/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그인처리 요청시 (포스트일땐) 실행
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // id 또는 pw가 비워 있을경우
        boolean isLoginFailed = false;
        if (id.isEmpty() || pw.isEmpty()) {
            isLoginFailed = true;
        }
        // 멤버스에서 키벨류(겟)로 읽음 , id를 equals 로 쓰면 null체크가 안됨
        if (members.get(id) == null ) {
            isLoginFailed = true;
        } else {
            if(!members.get(id).equals(pw)) {
                isLoginFailed = true;
            }
        }
        //로그인 실패시 정보를 담음
        if(isLoginFailed){
            request.setAttribute("loginFailed", isLoginFailed);
            // 로그인 성공시 세션에 담아줘야한다
        }else { // 자바엔 기본적으로 HttpSession 있음
            // 사용자의 정보를 세션에 저장
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/login/login.jsp");
        // 포워딩해서 jsp에 있는 정보를 확인, 해서 로그인 성공인지 실패인지 확인
        dispatcher.forward(request, response);
    }
}
