package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.Member;

public interface MemberDao {
    public Member getById(String login_id);
    public void getById(String name, String login_id, String password, String email);
    public String[] memberData(String id, String password);
    public void updataMember(String name, String memberId, String password, String passwordcheck, String email);

}
