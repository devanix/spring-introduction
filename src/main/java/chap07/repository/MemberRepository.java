package chap07.repository;

import chap07.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    chap07.domain.Member save(chap07.domain.Member member);
    Optional<chap07.domain.Member> findById(Long id);
    Optional<chap07.domain.Member> findByName(String name);
    List<Member> findAll();
}
