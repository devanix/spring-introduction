# ▋ 회원 관리 예제 - 웹 MVC 개발
*****

## ⎕ 회원 웹 기능 - 홈 화면 추가

### ❍ 홈 컨트롤러 추가
```java

@Controller
public class HomeConroller {
  @GetMapping("/home")
  public String home() {
    return "home";
  }
}
```

### ❍ 회원 관리용 홈
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
  <div>
    <h1>Hello Spring</h1>
    <p>회원 기능</p>
    <p>
      <a href="/members/new">회원 가입</a> <a href="/members">회원 목록</a>
    </p></div>
</div> <!-- /container -->
</body>
</html>
```