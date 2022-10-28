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

