package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll();
    public ArrayList<Board> getAll(Pagination pagination);
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination);
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination, String period);
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination, String period, String orderBy);
    public Board getById(Long id);
    public void save (Board board);
    public void update (Board board);
    public void delete (Board board);

}
