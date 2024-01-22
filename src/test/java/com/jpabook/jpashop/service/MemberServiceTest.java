package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;



@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setUsername("Kwon");
        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));


    }

    @Test
    public void 중복_회원_체크() {
        //given
        Member member1 = new Member();
        member1.setUsername("yoon");

        Member member2 = new Member();
        member2.setUsername("yoon");

        //when
        memberService.join(member1);

        //then
        assertThatThrownBy(
                () -> memberService.join(member2)
        ).isInstanceOf(IllegalStateException.class);
    }

}
