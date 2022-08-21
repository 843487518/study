package com.zxk.study.mapper;

import com.zxk.study.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insert(Member member);
    List<Member> selectByExample(Member member);
}
