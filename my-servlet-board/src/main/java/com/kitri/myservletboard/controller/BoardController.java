package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다</h1>");

        // URL을 파싱(잘게분석)해서 어떤 요청인지 파악
        out.println(request.getRequestURL());

        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI(); // /board/list
        String contextPath = request.getContextPath(); // /
        String command = requestURI.substring(contextPath.length()); // /board/list

        String view = "/view/board/";

//        out.println("command = " + command); // /board/create, command = /board/create

        if (command.equals("/board/list")) {

            // /board/list?page=3
            String page = request.getParameter("page");
            if (page == null) page = "1";
            Pagination pagination = new Pagination(Integer.parseInt(page));
            // 요청 : 게시글 리스트 좀 보여줘
            // 응답 : 게시글 리스트 페이지로 응답
            // ㄴ 리다이렉트로 응답해보기 : 번호만 줌 (내가직접 전화해야함) 꼭 리턴 넣기
//            response.sendRedirect("/view/board/list.jsp");
            // ㄴ 딜레이로 응답해보기 ( 2초뒤 응답 ) : 내선으로 연결해줌
//            response.addHeader("Refresh", "2; url = " + "/view/board/list.jsp");

//            pagination.setTotalRecords(boardService.getBoards().size()); // 총 게시글

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            String period = request.getParameter("period");

            ArrayList<Board> boards=
                    boardService.getBoards(type, keyword, pagination, period); // 게시판 리스트 가져옴

            // jsp에게 넘겨줘야함 ( request 저장소 안에 저장 )

            request.setAttribute("period", period);
            request.setAttribute("pagination", pagination); // 페이지네이션 정보
            request.setAttribute("type", type); // type 정보
            request.setAttribute("keyword", keyword); // keyword 정보
            request.setAttribute("boards",boards);
            view += "list.jsp";

        } else if (command.equals("/board/createForm")){
            // 요청 : 게시글 등록하게 등록폼 좀 줘
            // 응답 : 등록폼으로 응답
//            response.sendRedirect("/view/board/createForm.jsp");
            view += "createForm.jsp";
        } else if (command.equals("/board/create")){
            // 요청 : 게시판 이렇게 만들어줘
            // 응답 : 등록으로 응답
            // 데이터를 읽고 등록 시키면 된다
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = request.getParameter("writer");

            Board board = new Board(null, title, content, writer, LocalDateTime.now(), 0,0);
            boardService.addBoard(board);

            response.sendRedirect("/board/list");
            return;

        } else if (command.equals("/board/updateForm")){
            // 요청 : 게시판 이렇게 만들어줘
            // 응답 : 생성으로 응답
            Board board = boardService.getBoard(Long.parseLong(request.getParameter("id")));
            request.setAttribute("board", board);

            view += "updateForm.jsp";

//            response.sendRedirect("/view/board/updateForm.jsp");
        } else if (command.equals("/board/update")){
            // 요청 : 이 번호의 게시판 수정해줘
            // 응답 : 수정으로 응답

            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = request.getParameter("writer");

            boardService.updateBoard(new Board(id, title, content, null, null, 0, 0));

            response.sendRedirect("/board/list");
            return;

        } else if (command.equals("/board/delete")){
            Board board = boardService.getBoard(Long.parseLong(request.getParameter("id")));
            boardService.deleteBoard(board);
            
            response.sendRedirect("/board/list");
            return;
            // 요청 : 이 번호의 게시판 삭제 해줘
            // 응답 : 삭제로 응답
        } else if (command.contains("/board/detail")){
            // id에 해당하는 게시판 하나를 가져오면 된다
            // /board/detail>jd=3
            // 클라이언트가 요청한 HttpServletRequest 담아서
//            String queryString = request.getQueryString();
           Long id = Long.parseLong(request.getParameter("id"));
           Board board = boardService.getBoard(id);
           // board 데이터를 detail.jsp 에 전달하기 위해 어딘가에 담아줘야한다.
            request.setAttribute("board", board);

            view += "detail.jsp";
//            System.out.println();
        }
        // 뷰(페이지)를 응답하는 방법
        // 리다이렉트
        // 포워드
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        //  jsp 호출해줘 ( request 저장소 안에 jsp를 저장하지 않으면 호출해도 소용없음 )
        dispatcher.forward(request, response);
    }
}
