# ▋ 회원 관리 예제 - 백엔드

*****

## ⎕ 비즈니스 요구사항 정리

*****

* 데이터: 회원ID, 이름
* 기능: 회원 등록, 조회
* 아직 데이터 저장소가 선정되지 않음(가상의 시나리오)

#### **일반적인 웹 애플리케이션 계층 구조**

![image](https://user-images.githubusercontent.com/1131775/198696641-57b6552f-f57b-4c9c-adc2-2ce0d1951559.png)

* 컨트롤러: 웹 MVC의 컨트롤러 역할
* 서비스: 핵심 비즈니스 로직 구현
* 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
* 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

#### **클래스 의존관계**

![image](https://user-images.githubusercontent.com/1131775/198697069-d5794c0e-1bd7-4437-afe8-5b99320c109c.png)

* 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
* 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황으로 가정
* 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용

## ⎕ 회원 도메인과 리포지토리 만들기

*****

### ❍ 회원 객체(Member)

```java
public class Member {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### ❍ 회원 리포지토리 인터페이스

```java
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
```

### ❍ 회원 리포지토리 메모리 구현체

```java
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
```

## ⎕ 회원 리포지토리 테스트 케이스 작성
```textmate
개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 
웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
이러한 방법은 준비하고 실행하는데 오래 걸리고, 
반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다. 
자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
```

### ❍ 회원 리포지토리 메모리 구현체

`src/test/java` 하위 폴더에 생성

```java
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
```

* @AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다.<br> 
이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. <br>
![image](https://user-images.githubusercontent.com/1131775/198704490-50c266d3-836d-4530-b2e7-27bf06f749cd.png)

* @AfterEach 를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다. <br>
여기서는 메모리 DB에 저장된 데이터를 삭제한다.<br>
```java
  @AfterEach
  public void afterEach() {
      repository.clearStore();
  }
```
* 테스트는 각각 독립적으로 실행되어야 한다.<br>
테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.<br>


## ⎕ 회원 서비스 개발

```java
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
```

> <span style="color:#f2bc00">[NOTE] 네이밍 </span><br>
> * 서비스 클래스는 Join, findMembers와 같이 비즈니스와 같은 용어를 사용하는 것이 좋다.
> * 리포지토리 클래스는 기계적으로 개발스럽게 용어들을 선택.
 

## ⎕ 회원 서비스 테스트
*****

### ❍ DI 적용
﹅ **기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다.**
```java
public class MemberService {
      private final MemberRepository memberRepository =
                        new MemoryMemberRepository();
}
```

﹅ **회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경한다.**
```java
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
...
}
```

﹅ **멤버 서비스 테스트 코드**
```java
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService ;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {

        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
```

* @BeforeEach : 각 테스트 실행 전에 호출된다.<br> 
테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.<br>

