# ▋ 스프링 웹 개발 기초
*****

* 정적 컨텐츠
* MVC와 템플릿 엔진
* API

## ⎕ 정적 컨텐츠
*****
[스프링 부트 정적 컨텐츠 기능](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)

 ﹅resources/static/hello-static.html
```html
<!DOCTYPE HTML>
  <html>
  <head>
      <title>static content</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
<body>
정적 컨텐츠 입니다.
  </body>
  </html>
```

**출력 화면**

동작 확인: http://localhost:8080/hello-static.html

<img width="398" alt="image" src="https://user-images.githubusercontent.com/1131775/198547494-6541940a-0dbf-4843-ac86-e1dd39b23a6a.png">


#### **정적 컨텐츠 이미지**

<img width="957" alt="image" src="https://user-images.githubusercontent.com/1131775/198547975-1fcf3fb8-ab4f-449a-b1ed-4c708cc893c2.png">
1) 내장 통켓 서버에서 스프링 컨테이너의 컨트롤러 확인<br>
2) 리소스(resources)/static 확인


## ⎕ MVC와 템플릿 엔진
*****
MVC: Model, View, Controller

﹅ **Controller**</br>
```java
@Controller
  public class HelloController {
      @GetMapping("hello-mvc")
      public String helloMvc(@RequestParam("name") String name, Model model) {
          model.addAttribute("name", name);
          return "hello-template";
      }
}
```

﹅ **View**</br>
resources/templates/hello-template.html
```html
<html xmlns:th="http://www.thymeleaf.org">
  <body>
  <p th:text="'hello ' + ${name}">hello! empty</p>
  </body>
</html>
```

﹅ **실행**</br>
http://localhost:8080/hello-mvc?name=spring

> <font style="color:#f2bc00">[NOTE] url에 name 파라미터가 없을 경우 에러 출력</font>
> ```pycon
> Resolved [org.springframework.web.bind.MissingServletRequestParameterException: Required request parameter 'name' for method parameter type String is not present]
> ```

﹅ **MVC, 템플릿 엔진 이미지**<br>
<img width="951" alt="image" src="https://user-images.githubusercontent.com/1131775/198550257-951410d1-fb5f-43b7-90cf-cbc12931033c.png">

