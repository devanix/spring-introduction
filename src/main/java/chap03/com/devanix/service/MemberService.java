package chap03.com.devanix.service;

import chap03.com.devanix.domain.Member;
import chap03.com.devanix.repository.MemberRepository;
import chap03.com.devanix.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 이름 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent( m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    /**
     * 회원 찾기
     *  - 아이디로 하나의 회원 찾기
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
