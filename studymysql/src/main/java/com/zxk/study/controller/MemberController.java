package com.zxk.study.controller;

import com.zxk.study.entity.Member;
import com.zxk.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/6/2  21:20
 */

@Controller
@RequestMapping("/api/v1")
public class MemberController {
    @Autowired
    MemberService memberService;

    @ResponseBody
    @GetMapping("/member/one/{id}")
    public List<Member> selectOneById(@PathVariable("id") long id){
        Member member = new Member();
        member.setId(id);
        return memberService.selectAll(member);
    }

    @ResponseBody
    @GetMapping("/member/inser/{mname}")
    public Integer insertone(@PathVariable("mname") String name){
        Member member = new Member();
        member.setMemberName(name);
        return memberService.insert(member);
    }

}
