## Web Server
웹 브라우저를 클라이언트로부터 HTTP요청을 받아들이고 HTML 문서와 같은 웹 페이지를 반환하는 컴퓨터 프로그램

## Web Server 예시
Apache, Nginx, node.js (자체 내장 서버)

## Apache
클라이언트에서 요청한 HTTP Request를 처리하는 웹서버 이다. 아파치는 정적 타입의 데이터만 처리

## WAS (Web Application Server)
- 동적인 자료를 처리하는 서버
- WAS는 웹서버 + 서블릿 컨테이너 (웹 컨테이너) 로 구성되어 있다.
- 클라이언트에서 HTTP Request를 보내면 먼저 웹서버를 통해 정적데이터 처리만 필요한 요청인지 확인한다. 이후 정적 데이터 처리만 필요하다면 그대로 웹서버에서 요청에 대한 응답을 다시 클라이언트로 보내준다.
- 그러나 동적데이터 처리가 필요한 요청이라면 이를 서블릿 컨테이너(웹컨테이너) 에 넘겨준다. 서블릿 컨테이너는 요청정보를 파악하여 실시간으로 페이지에 필요한 파일을 생성한다. 요청이 올 때마다 페이지에 필요한 정보를 그때그때 생성하므로 서버의 리소스의 부하를 줄일 수 있다는 장점이 있다.

## WAS 예시
Tomcat, JBoss, Jeus

## Tomcat
동적인 웹을 만들기 위한 웹 컨테이너, JSP와 Servlet을 구동하기 위한 서블릿 컨테이너 역할을 수행

## Web server와 WAS의 구조
![image](https://github.com/user-attachments/assets/c2996112-0644-4f54-9db5-fe5b4064842c)
사용자 (Client)가 HTTP Request를 던졌을 때 필요한 데이터가 정적데이터 라면 Web 서버(Apache) 에서는 바로 HTTP Response를 통해 정적 HTML을 반환하고 동적 데이터라면 이를 Web Container(Servlet Container) 로 보내 동적 데이터 처리를 한 뒤 Web 서버를 통해 사용자에게 반환한다.

기본적으로 아파치와 톰캣의 기능은 나뉘어져 있지만, 톰캣 안의 컨테이너를 통해 일부 아파치 기능을 발휘하기 때문에 아파치톰캣 이라고 합쳐서 부른다. 아파치만 쓰면 정적 웹페이지만 처리가 가능하다

## Tomcat만 사용한다면?
톰캣만 쓰면 정적 웹페이지 + 동적 웹페이지 처리가 모두 가능하지만, 아파치에서 필요한 기능을 가져올 수 없고, 과부하가 걸릴 가능성이 높다.
따라서 아파치와 톰캣을 같이 사용하여 아파치는 정적 데이터 처리, 톰캣은 JSP, ASP, PHP 등 동적 데이터 처리를 분담한다.

> 서블릿 컨테이너는 MVC 패턴에서 Controller에 주로 사용된다.

## Client -> web server -> WAS -> DB 동작 과정
2. Web Server는 클라이언트의 요청(Request)을 WAS에 보낸다.
3. WAS는 관련된 Servlet을 메모리에 올린다.
4. WAS는 web.xml을 참조하여 해당 Servlet에 대한 Thread를 생성한다. (Thread Pool 이용)
5. HttpServletRequest와 HttpServletResponse 객체를 생성하여 Servlet에 전달한다. <br>
**5-1.** Thread는 Servlet의 service() 메서드를 호출한다. <br>
**5-2.** service() 메서드는 요청에 맞게 doGet() 또는 doPost() 메서드를 호출한다. <br>
**5-3.** protected doGet(HttpServletRequest request, HttpServletResponse response)
6. doGet() 또는 doPost() 메서드는 인자에 맞게 생성된 적절한 동적 페이지를 Response 객체에 담아 WAS에 전달한다.
7. WAS는 Response 객체를 HttpResponse 형태로 바꾸어 Web Server에 전달한다.
8. 생성된 Thread를 종료하고, HttpServletRequest와 HttpServletResponse 객체를 제거한다.

## Web server와 WAS를 구분하는 이유
> 자원 이용의 효율성 및 장애 극복, 배포 및 유지보수의 편의성을 위해 Web Server와 WAS를 분리한다. Web Server를 WAS 앞에 두고 필요한 WAS들을 Web Server에 플러그인 형태로 설정하면 더욱 효율적인 **분산 처리**가 가능하다.

### 1. 기능을 분리하여 서버 부하 방지

WAS는 DB 조회나 다양한 로직을 처리하느라 바쁘기 때문에 단순한 정적 컨텐츠는 Web Server에서 빠르게 클라이언트에 제공하는 것이 좋다.

### 2. 물리적으로 분리하여 보안 강화

SSL에 대한 암복호화 처리에 Web Server를 사용

### 3. 여러 대의 WAS를 연결 가능

대용량 웹 어플리케이션의 경우(여러 개의 서버 사용) Web Server와 WAS를 분리하여 무중단 운영을 위한 장애 극복에 쉽게 대응할 수 있다. 
예를 들어, 앞 단의 Web Server에서 오류가 발생한 WAS를 이용하지 못하도록 한 후 WAS를 재시작함으로써 사용자는 오류를 느끼지 못하고 이용할 수 있다.

### 4. 여러 웹 어플리케이션 서비스 가능

예를 들어, 하나의 서버에서 PHP Application과 Java Application을 함께 사용하는 경우

## 참고 자료
https://idkim97.github.io/2022-08-22-%EC%95%84%ED%8C%8C%EC%B9%98(Apache)%EC%99%80%20%ED%86%B0%EC%BA%A3(Tomcat)/
https://velog.io/@falling_star3/web-Web-Server%EC%99%80-WASWeb-Application-Server
