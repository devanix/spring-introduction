package chap07.service;


import chap07.domain.Member;
import chap07.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final chap07.repository.MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(chap07.domain.Member member) {
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 이름 검증
     * @param member
     */
    private void validateDuplicateMember(chap07.domain.Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent( m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<chap07.domain.Member> findMembers() {
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
