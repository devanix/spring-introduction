package com.jwlim.controller.service;

import com.jwlim.controller.domain.Member;
import com.jwlim.controller.repository.MemberRepository;
import com.jwlim.controller.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository repository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        this.repository = new MemoryMemberRepository();
        this.memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long result = memberService.join(member);

        //then
        Member findMember = memberService.findOne(result).get();
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    void validateDuplicateMember() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}