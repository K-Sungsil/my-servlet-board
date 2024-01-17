package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.board.BoardDao;
import com.kitri.myservletboard.dao.board.BoardJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;

public class BoardService {
    BoardDao boardDao = BoardJdbcDao.getInstance();
    private BoardService(){};
    private static final BoardService instance = new BoardService();
    public static BoardService getInstance() { return instance;}

    //  게시판 하나를 가져오는 로직
    public Board getBoard(Long id){
        return boardDao.getById(id);
    }
    // 게시판 리스트 가져오는 로직 (전체조회)
    public ArrayList<Board> getBoards() { return boardDao.getAll(); }
    //
    public ArrayList<Board> getBoards(Pagination pagination) {

        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count()); // totalRecord의 값 계산
        pagination.calcPagination();

        return boardDao.getAll(pagination); }

    public ArrayList<Board> getBoards(String type, String keyword, Pagination pagination) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count(type, keyword)); // totalRecord의 값 계산2
        pagination.calcPagination();

        return boardDao.getAll(type, keyword, pagination); }

    public ArrayList<Board> getBoards(String type, String keyword, Pagination pagination, String period) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count(type, keyword, period)); // totalRecord의 값 계산3
        pagination.calcPagination();

        return boardDao.getAll(type, keyword, pagination, period); }
    public ArrayList<Board> getBoards(String type, String keyword, Pagination pagination, String period, String orderBy) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count(type, keyword, period)); // totalRecord의 값 계산3
        pagination.calcPagination();

        return boardDao.getAll(type, keyword, pagination, period, orderBy); }



    // 게시글 등록
    public void addBoard(Board board){
        boardDao.save(board);
    }
    // 게시글 수정
    public void updateBoard(Board board){
        boardDao.update(board);
    }
    // 게시글 삭제
    public void deleteBoard(Board board){
        boardDao.delete(board);
    }

    public String[] memberData(String id, String password) { return boardDao.memberData(id, password); }

}
