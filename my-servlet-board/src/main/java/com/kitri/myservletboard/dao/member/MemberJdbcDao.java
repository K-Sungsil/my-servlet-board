package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                String name = rs.getString("name");
                String loginId = rs.getString("login_id");
                String password = rs.getString("password");
                String email = rs.getString("email");

                Member member1 = new Member(name, loginId, password, email);
                return member1;
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
}
