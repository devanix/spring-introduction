package chap07.repository;

import chap07.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public chap07.domain.Member save(chap07.domain.Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<chap07.domain.Member> findById(Long id) {
        chap07.domain.Member member = em.find(chap07.domain.Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<chap07.domain.Member> findByName(String name) {
        List<chap07.domain.Member> result = em.createQuery("select m from Member m where m.name = :name", chap07.domain.Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<chap07.domain.Member> findAll() {
        return em.createQuery("select m from Member m", chap07.domain.Member.class)
                .getResultList();
    }
}
