package com.jwlim.controller.service;

import com.jwlim.controller.domain.Member;
import com.jwlim.controller.repository.MemberRepository;
import com.jwlim.controller.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository repository;

    public MemberService(MemberRepository memberRepository) {
        this.repository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        repository.save(member).getId();
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}
