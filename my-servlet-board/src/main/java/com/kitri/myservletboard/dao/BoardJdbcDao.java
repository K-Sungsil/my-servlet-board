package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardJdbcDao implements BoardDao {
    private static final BoardJdbcDao instance = new BoardJdbcDao();
    public static BoardJdbcDao getInstance(){
        return instance;
    }
    public Connection connectDB() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my_servlet_board";
            String user = "root";
            String pwd = "1234";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
            ps.executeQuery(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }


        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards;
    }

    @Override
    public Board getById(Long id) {
        // connection
        // ps -> executeQuery();
        // rs

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Board Board = new Board();

        try {
            connection =connectDB();
            String sql = "SELECT * FROM board WHERE id=?";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");
                Board board = new Board(id, title, content, writer, createdAt,viewCount, commentCount);
                return board;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Board;
    }

    @Override
    public void save(Board board) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn =connectDB();

            String sql = "INSERT INTO board (title, content, writer) VALUES(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.executeUpdate();

        } catch (Exception e) {

        }  finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Board board) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn =connectDB();

            String sql = "UPDATE board SET title = ?,  content = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setLong(3, board.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Board board) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn =connectDB();

            String sql = "DELETE FROM board WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, board.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}