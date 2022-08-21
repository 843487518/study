package com.zxk.study;

import com.zxk.study.entity.Member;
import com.zxk.study.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudymysqlApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
//    private MemberService memberService;
//
//    @Test
//    public void testWrite() {
//        Member member = new Member();
//        member.setMemberName("李四");
//        memberService.insert(member);
//    }
//
//    @Test
//    public void testRead() {
//        for (int i = 0; i < 4; i++) {
//            memberService.selectAll(new Member());
//        }
//    }
//
//    @Test
//    public void testSave() {
//        Member member = new Member();
//        member.setMemberName("王二麻子");
//        memberService.save(member);
//    }
//
//    @Test
//    public void testReadFromMaster() {
//        memberService.getToken("1234");
//    }

}
