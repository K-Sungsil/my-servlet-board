package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

public class BoardService {
    BoardDao boardDao = BoardMemoryDao.getInstance();
    private BoardService() {};
    private static final BoardService instance = new BoardService();
    public static BoardService getInstance(){
        return instance;
    }
    //  게시판 하나를 가져오는 로직
    public Board getBoard(Long id){
        return boardDao.getById(id);
    }
    // 게시판 리스트 가져오는 로직
    public ArrayList<Board> getBoards() { return boardDao.getAll(); }
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

}
