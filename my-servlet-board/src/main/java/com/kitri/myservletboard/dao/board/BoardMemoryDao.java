package com.kitri.myservletboard.dao.board;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao{
    private static final BoardMemoryDao instance = new BoardMemoryDao();
    public static BoardMemoryDao getInstance(){
        return instance;
    }
    ArrayList<Board> BoardMemoryDB = new ArrayList<>();

    private BoardMemoryDao(){
    }

    @Override
    public ArrayList<Board> getAll() {
        return BoardMemoryDB;
    }

    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
        return null;
    }

    @Override
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination) {
        return null;
    }

    @Override
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination, String period) {
        return null;
    }

    @Override
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination, String period, String orderBy) {
        return null;
    }

    @Override
    public Board getById(Long id) {
        return BoardMemoryDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }

    @Override
    public void save(Board board) {
        // id 자동생성 로직 ( 단, id 가 기존의 id 와 중복되지 않도록)
        Long id = 0L;
        boolean flag = false;
        while (!flag) {
            flag = true;
            id++; // 1씩 증가
            for(Board board_ : BoardMemoryDB ){
                if (id == board_.getId()){
                    // 중복
                    flag = false;
                    break;
                }
            }
        }
        board.setId(id);
        BoardMemoryDB.add(board);

    }

    @Override
    public void update(Board board) {
        Board board_ = getById(board.getId());
        board_.setTitle(board.getTitle());
        board_.setContent(board.getContent());
//        BoardMemoryDB.remove(board_);
//        BoardMemoryDB.add(board);

    }

    @Override
    public void delete(Board board) {
        Board board_ = getById(board.getId());
        BoardMemoryDB.remove(board_);
    }

    @Override
    public String[] memberData(String id, String password) {
        return new String[0];
    }
}
