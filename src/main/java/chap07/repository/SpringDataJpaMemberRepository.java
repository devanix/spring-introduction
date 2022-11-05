package chap07.repository;

import chap07.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<chap07.domain.Member, Long>, MemberRepository {
    Optional<Member> findByName(String name);
}
