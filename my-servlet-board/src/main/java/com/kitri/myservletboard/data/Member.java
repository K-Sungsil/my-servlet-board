package com.kitri.myservletboard.data;

public class Member {
    private Long id;
    private String loginId;
    private String password;
    private String passwordcheck;
    private String name;
    private String email;

    public Member(){

    }

    public Member(String name, String loginId, String password, String email) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }

    public Member(String loginId, String password, String passwordcheck, String name, String email) {
        this.loginId = loginId;
        this.password = password;
        this.passwordcheck = passwordcheck;
        this.name = name;
        this.email = email;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
