package com.zxk.study.service;

import com.zxk.study.entity.Member;

import java.util.List;

public interface MemberService {
    public int insert(Member member);
    public int save(Member member);
    public List<Member> selectAll(Member member);
    public String getToken(String appId);
}
