package chap07.service;

import chap07.domain.Member;
import chap07.repository.MemberRepository;
import chap07.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

     @Test
    public void 회원가입() throws Exception {
         // Given
         Member member = new Member();
         member.setName("devanix");

         // when
         Long saveId = memberService.join(member);

         // Then
         Member findMember = memberService.findOne(saveId).get();
         assertEquals(member.getName(), findMember.getName());
     }

     @Test
    public void 중복회원예외() {
         // Given
         Member member1 = new Member();
         member1.setName("Luka");

         Member member2 = new Member();
         member2.setName("Luka");

         // When
         memberService.join(member1);
         IllegalStateException e = assertThrows(IllegalStateException.class,
                 () -> memberService.join(member2));// 예외가 발생해야 한다.


         assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
     }

}
