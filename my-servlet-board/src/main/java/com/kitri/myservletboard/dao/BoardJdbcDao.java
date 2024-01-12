package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

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
    public ArrayList<Board> getAll(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board LIMIT ?,?"; // LIMIT 0,10 첫번째 페이지/LIMIT 10,10 두번째 페이지/LIMIT 20,10 세번째페이지
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() - 1) * pagination.getMaxRecordsPerpage()); // 페이지-1 곱하기 10해야 첫번째 0,10,20 ... 숫자나옴
            ps.setInt(2, pagination.getMaxRecordsPerpage());
            rs = ps.executeQuery();

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
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        if (type == null){
            type="title";
        }
        if (keyword == null) {
            keyword="";
        }
        try {
            connection = connectDB();
            String sql = "SELECT * FROM board WHERE "+ type +" LIKE "+ "'%" + keyword +"%'"+" LIMIT ?,?"; // LIMIT 0,10 첫번째 페이지/LIMIT 10,10 두번째 페이지/LIMIT 20,10 세번째페이지
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() - 1) * pagination.getMaxRecordsPerpage()); // 페이지-1 곱하기 10해야 첫번째 0,10,20 ... 숫자나옴
            ps.setInt(2, pagination.getMaxRecordsPerpage());
            rs = ps.executeQuery();

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
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination, String period) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        if (type == null){
            type="title";
        }
        if (keyword == null) {
            keyword="";
        }if (period == null) {
            period="100 year";
        }
        try {
            connection = connectDB();
            String sql = "SELECT * FROM board WHERE " + type + " LIKE " + "'%" + keyword + "%'" + " AND created_at " + "BETWEEN DATE_ADD(NOW(), INTERVAL -" + period + ") AND NOW() LIMIT ?,?"; // LIMIT 0,10 첫번째 페이지/LIMIT 10,10 두번째 페이지/LIMIT 20,10 세번째페이지
            // "SELECT * FROM board WHERE " + writer + " LIKE " + "'%" + 희망찬 + "%'" + " AND " + created_at + " BETWEEN DATE_ADD(NOW(), INTERVAL -1 DAY) AND NOW() LIMIT 10,10" ;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() - 1) * pagination.getMaxRecordsPerpage()); // 페이지-1 곱하기 10해야 첫번째 0,10,20 ... 숫자나옴
            ps.setInt(2, pagination.getMaxRecordsPerpage());
            rs = ps.executeQuery();

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
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board LIMIT ?,10";
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
    public int count() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;

        try {
            connection = connectDB();

            String sql = "SELECT count(*) FROM board";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();

            count = rs.getInt("count(*)");

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
        return count;
    };

    public int count(String type, String keyword) {
        if (keyword == null) {
            return count();
        } else {
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            int count = 0;

            try {
                connection = connectDB();


                String sql = "SELECT count(*) FROM board WHERE "+ type +" LIKE "+ "'%" + keyword +"%'";

                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                rs.next();

                count = rs.getInt("count(*)");

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
            return count;
        }
    };
    public int count(String type, String keyword, String period) {
        if (keyword == null | period == null) {
            return count();
        } else {
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            int count = 0;

            try {
                connection = connectDB();


                String sql = "SELECT count(*) FROM board WHERE "+ type +" LIKE "+ "'%" + keyword +"%'" + " AND created_at " + "BETWEEN DATE_ADD(NOW(), INTERVAL -" + period + ") AND NOW()";

                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                rs.next();

                count = rs.getInt("count(*)");

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
            return count;
        }
    };
}
