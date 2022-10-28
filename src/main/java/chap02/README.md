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

