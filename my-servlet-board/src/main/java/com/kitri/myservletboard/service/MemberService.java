package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.member.MemberDao;
import com.kitri.myservletboard.dao.member.MemberJdbcDao;
import com.kitri.myservletboard.data.Member;

public class MemberService {
    MemberDao memberDao = MemberJdbcDao.getInstance();
    private static final MemberService instance = new MemberService();
    public static MemberService getInstance() {
        return instance;
    }
    private MemberService(){};

    public Member getMember(String loginId) {
        return memberDao.getById(loginId);
    }
    public void getMember(String name, String loginId, String password, String email) {
        memberDao.getById(name, loginId, password, email);
    }

}
