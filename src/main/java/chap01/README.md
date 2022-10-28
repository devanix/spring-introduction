# 프로젝트 환경설정
*****

## 프로젝트 생성
*****
* [start.spring.io](https://github.com/devanix/spring-introduction) 에서 아래와 같이 프로젝트 생성
> * Project: Gradle Project
> * Spring Boot: 2.3.1
> * Dependencies:
>   * Spring Web
>   * Thymeleaf

> <font style="color:#f2bc00">[NOTE]</font> 
> 첫 Gradle 프로젝트 로딩 시 관련 라이브러리를 다운로드 하고 세팅 함으로 시간이 걸릴 수 있다.

### 디렉토리 구조
*****
![image](https://user-images.githubusercontent.com/1131775/198495020-93844eef-5c5b-454a-918a-dd7729747465.png)

> <font style="color:#f2bc00">[NOTE]</font>
> build.gradle 파일은 start.spring.io에서 생성 해서 복사&붙여넣기 수행

### main 생성 및 실행
*****

```java
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}
```
 
* 실행 후 화면
  * `Completed initialization in 0 ms` 메시지 나오면 구동 성공
  * ![image](https://user-images.githubusercontent.com/1131775/198497909-ef600586-821f-4c52-b5d2-ea2715f58b60.png)
  * `Tomcat started on port(s): 8080 (http) with context path` 포트 확인
    * http://localhost:8080 접속 확인 - Whitelabel Error Page


> <font style="color:#f2bc00">[NOTE] Intellij 설정</font></br>
> 빌드 실행 시 Java 직접 실행되는 것이 아닌 Gradle을 통해 실행될 때 느릴 때가 있음</br>
> 아래처럼 Intellij Gradle 구성을 변경하여 실행한다.
![image](https://user-images.githubusercontent.com/1131775/198498634-56b738f0-9de0-41a2-a765-df6674fe2478.png)


## 라이브러리 살펴보기
*****

> Gradle은 의존관계가 있는 라이브러리를 함께 다운로드 한다.

#### 스프링 부트 라이브러리
* spring-boot-starter-web 
  * spring-boot-starter-tomcat: 톰캣 (웹서버) 
  * spring-webmvc: 스프링 웹 MVC 
* spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View) 
* spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅 
  * spring-boot 
    * spring-core 
  * spring-boot-starter-logging 
    * logback, slf4j
    

#### 테스트 라이브러리
* spring-boot-starter-test 
  * junit: 테스트 프레임워크 
  * mockito: 목 라이브러리 
  * assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리 
  * spring-test: 스프링 통합 테스트 지원


> <font style="color:#f2bc00">[NOTE] gradle 탭에서 의존 파일 확인</font></br>
> <라이브러리>(\*) 에서 '(*)' 중복 제거 의미 (그래이들 관리)</br>
> ![image](https://user-images.githubusercontent.com/1131775/198501966-9c286c4b-3d44-4217-b6ed-3038a2b2df32.png)