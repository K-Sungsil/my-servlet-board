package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao{
    private static final BoardMemoryDao instance = new BoardMemoryDao();
    public static BoardMemoryDao getInstance(){
        return instance;
    }
    ArrayList<Board> BoardMemoryDB = new ArrayList<>();

    private BoardMemoryDao(){
        BoardMemoryDB.add(new Board(1L, "첫 번째 글!!", "반갑습니다", "김동현", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(2L, "두 번째 글!!", "반갑습니다", "김미성", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(3L, "세 번째 글!!", "반갑습니다", "김성실", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(4L, "네 번째 글!!", "반갑습니다", "박세환", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(5L, "다섯 번째 글!!", "반갑습니다", "박준혁", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(6L, "여섯 번째 글!!", "반갑습니다", "박준형", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(7L, "일곱 번째 글!!", "반갑습니다", "박현오", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(8L, "여덟 번째 글!!", "반갑습니다", "오시현", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(9L, "아홉 번째 글!!", "반갑습니다", "주나영", LocalDateTime.now(), 10, 2));
        BoardMemoryDB.add(new Board(10L, "열 번째 글!!", "반갑습니다", "한민선", LocalDateTime.now(), 10, 2));
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

}
