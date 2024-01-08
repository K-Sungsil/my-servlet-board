package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoarDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

public class BoardService {
    BoarDao boarDao = BoardMemoryDao.getInstance();
    private BoardService() {};
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance() {
        return instance;
    }
        public Board getBoards(Long id){
            return boarDao.getById(id);
        }
        public ArrayList<Board> getBoards(){
            return boarDao.getAll();
        }
        public void addBoard(Board board) {
            boarDao.save(board);
        }
        public void updateBoard(Board board) {
            boarDao.update(board);
        }
        public void deleteBoard(Board board) {
            boarDao.delete(board);
        }
}
