package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class MemberJdbcDao implements MemberDao {
    private static final MemberJdbcDao instance = new MemberJdbcDao();
    public static MemberJdbcDao getInstance() {return instance;}
    private MemberJdbcDao(){}
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
    public Member getById(String login_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Member member = new Member();

        try{
            conn = connectDB();
            String sql = "SELECT * FROM member WHERE login_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, login_id);
            rs = ps.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String loginId = rs.getString("login_id");
                String password = rs.getString("password");
                String email = rs.getString("email");

                member = new Member(name, loginId, password, email);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return member;
        }

    @Override
    public void getById(String name, String login_id, String password, String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = connectDB();
            String sql = "INSERT INTO member (name, login_id, password, email) VALUES(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, login_id);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String[] memberData(String loginId, String loginPw) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] member = new String[5];
        try{
            conn = connectDB();
            String sql = "SELECT * FROM member where login_id = '" + loginId + "' and password = '" + loginPw + "'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if ( rs.next() ) {
                member[0] = String.valueOf(rs.getLong("id"));
                member[1] = rs.getString("login_id");
                member[2] = rs.getString("password");
                member[3] = rs.getString("name");
                member[4] = rs.getString("email");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return member;
    }

    @Override
    public void updataMember(String name, String memberId, String password, String passwordcheck, String email) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn =connectDB();

            String sql = "UPDATE member SET name = ?,  password = ?, email = ? WHERE login_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, memberId);
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
