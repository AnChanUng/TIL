## Tomcat
- tomcat은 Apache재단에서 관리되며 Java 표준 인터페이스인 Servlet을 지원하기 위한 미들웨어
- tomcat은 OS로부터 네트워크 요청 정보를 받아와 자바 객체로 만들고 이를 Servlet Container로 위임
- 요청하는 자원이 jsp나 자바코드로 적혀있는 자원을 요청하게 되면 아파치는 자바코드를 이해하지 못해서 응답할 수 없다. 그래서 아파치에 tomcat을 달면 아파치가 이해하지 못하는 자원을 받았을 때 tomcat한테 제어권을 넘겨버림

![image](https://github.com/user-attachments/assets/a97f796b-9763-4ad7-a8fc-ce3e00537157)


## Tomcat WAS의 주요 기능
- OS로 부터 데이터 패킷을 받아 Servlet Container에 요청을 넘김. 이과정에서 tomcat은 socket I/O를 OS의 도움을 받아 처리.
- 동적인 컨테츠를 제공하기 위해 DB연결을 지원해주고 transaction을 관리 (tomcat-jdbc-pool 및 hikari 구현체)

## Tomcat HTTP 요청을 받아들이는 I/O 
### 네트워크 패킷부터 Spring Container까지 요청이 도착하는 과정
![image](https://github.com/user-attachments/assets/1caefcd9-7543-46d5-8277-507fcb6b63c8)
1. OS 시스템 콜을 통해 이벤트 발생에 대한 port를 listen(소켓통신에서 client-serer 간 통신 과정 중 일부)하고 Socket Connection을 획득
2. Socket Connection으로부터 패킷 획득하고 WAS가 해당 데이터를 파싱 해 HttpServletRequest(ServletRequest 인터페이스의 구현체) 객체 생성
3. 생성한 HttpServletRequest 객체를 서블릿 컨테이너로 위임

![image](https://github.com/user-attachments/assets/3edfe304-f7fe-46c2-8ef0-af5d4b7fdae0)
1. 서블릿 컨테이너에서 모든 요청을 Dispatcher Servlet이 요청을 받고 받은 요청에 해당하는 컨트롤러(핸들러)를 찾기 위한 과정 진행
2. Dispatcher Servlet은 HandlerMapping과 HandlerAdapter를 인터페이스로 가지고 있고, 스프링 프레임워크가 애플리케이션이 처음 시작할 때 해당 인터페이스에 미리 다양한 구현체들을 주입함. Dispatcher Servlet은 현재 요청에 맞는 HandlerMapping과 HandlerAdapter의 구현체를 찾아 요청을 위임할 컨트롤러 결정
3. 결정된 컨트롤러로 요청 위임
4. 스프링 컨텍스트(root application context)에서 비즈니스 로직 처리 및 DB connection 관리
 
## Tomcat Connector
- 톰캣으로 요청이 들어오는 첫 번째 문은 Connector이다.
- 톰캣의 Connector는 호스트 PC로 들어오는 특정 TCP 포트의 요청들을 listen 하여 요청이 해당 포트의 애플리케이션으로 들어올 수 있도록 한다.
- 즉, Connector는 소켓 연결을 listen 하면서 들어오는 패킷을 파싱 해 HttpServletRequest 객체로 변환하여 서블릿 컨테이너로 요청을 위임하는 역할을 담당한다.
- 그러면 서블릿 컨테이너에서는 핸들러 매핑 전략에 의해 알맞은 핸들러를 찾고 비즈니스 로직이 수행된다.

- HTTP를 비롯한 다양한 애플리케이션 레이어의 프로토콜을 처리할 수 있도록 개발되었고 각 프로토콜마다 해당하는 Connector가 존재한다. 대표적으로 HTTP/1.1 Connector, HTTP/2 Connector 등이 있다.

## Connector 종류
- BIO, NIO, NIO2, APR (tomcat-10.0부터 삭제)

<br>

## BIO Connector (Connection Per Thread, Request Per thread)
- BIO (Blocking I/O) Connector는 Java에서 서블릿 컨테이너에서 사용되는 전통적인 I/O 방식
- BIO 방식은 하나의 요청(클라이언트)당 하나의 스레드를 할당하는 구조
- 즉, 클라이언트가 접속할 때마다 새로운 스레드가 생성되어 요청을 처리

## BIO Connector의 동작 방식
- **Connection Per Thread**
    - 클라이언트가 서버에 연결할 때마다 **새로운 스레드가 생성**됩니다.
    - 스레드는 클라이언트와 1:1로 매핑되어 연결을 유지하고 요청을 처리합니다.
    - 클라이언트가 요청을 보내지 않고 대기 상태일 때도 스레드는 계속 유지됩니다.
    - 연결이 종료될 때까지 스레드는 반환되지 않습니다.
- **Request Per Thread**
    - 클라이언트의 **요청 하나당 하나의 스레드**가 사용됩니다.
    - 하나의 연결이 여러 요청을 보낸다면, 각 요청에 대해 새로운 스레드가 생성됩니다.
    - 요청이 완료되면 스레드는 반환됩니다.
 
## BIO Connector 동작 예시
```
ServerSocket serverSocket = new ServerSocket(8080);

while (true) {
    Socket clientSocket = serverSocket.accept(); // 클라이언트 연결 대기 (블로킹)
    new Thread(() -> {
        try (InputStream in = clientSocket.getInputStream();
             OutputStream out = clientSocket.getOutputStream()) {
            
            // 요청 읽기 및 처리
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            String request = new String(buffer, 0, read);
            System.out.println("Request: " + request);
            
            // 응답
            String response = "HTTP/1.1 200 OK\r\n\r\nHello World!";
            out.write(response.getBytes());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }).start();  // 연결마다 새로운 스레드 생성
}
```
- 클라이언트가 `accept()`를 호출하면 **새로운 스레드**가 생성됩니다.
- 요청을 처리하는 동안 해당 스레드는 **블로킹 상태**가 됩니다.
  
## NIO Connector (NIO의 Selector 기반 Multiplexing)
![image](https://github.com/user-attachments/assets/a7ef7292-0621-4383-ba37-a887edf9cba3)
- NIO (New I/O)는 **Java 1.4**에서 도입된 **Non-blocking I/O 방식**입니다.
- Tomcat 같은 서버 애플리케이션에서 **동시성 문제를 해결하고 성능을 향상**시키기 위해 사용됩니다.
- BIO와 달리 **하나의 스레드**로 **여러 클라이언트 연결을 처리**할 수 있습니다.
- 이를 통해 **동시 처리 성능이 향상되고 자원 낭비가 줄어듭니다.**

## NIO Connector의 동작 방식

NIO의 핵심은 **Selector**와 **Channel**을 사용하는 Multiplexing (다중화)입니다.

- **Selector**: 여러 채널(Channel)을 **모니터링**하고, 준비된 채널(클라이언트)을 감지해 **이벤트 기반으로 처리**합니다.
- **Channel**: 클라이언트와 연결되는 I/O 스트림입니다. BIO에서 `Socket`을 사용하던 방식과 유사합니다.
- **Multiplexing (다중화)**: 하나의 스레드가 여러 클라이언트의 요청을 감지하고 처리하는 방식입니다.

## NIO Connector 동작 흐름

1. **서버는 Selector를 생성**하고, **클라이언트의 요청이 발생하면** 이를 감지합니다.
2. **하나의 스레드가 여러 클라이언트의 연결을 처리**하며, 요청이 없는 경우 스레드는 다른 작업을 수행하거나 대기합니다.
3. 클라이언트의 **데이터가 준비되었을 때만** 스레드가 해당 작업을 처리합니다.
4. 각 클라이언트는 `Channel`을 통해 데이터 통신을 수행하며, `Selector`는 **준비 상태의 채널만** 처리합니다.

## NIO Connector 동작 예시 (Selector 기반)
```
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open(); // Selector 생성
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8080));
        serverChannel.configureBlocking(false);  // Non-blocking 설정
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);  // 연결 수락 이벤트 등록
        
        while (true) {
            selector.select();  // 클라이언트 연결 또는 데이터 준비 시 블로킹 해제
            
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                
                if (key.isAcceptable()) {
                    // 클라이언트 연결 수락
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);  // 읽기 이벤트 등록
                } else if (key.isReadable()) {
                    // 클라이언트 데이터 읽기
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = client.read(buffer);
                    
                    if (bytesRead == -1) {
                        client.close();
                        continue;
                    }
                    
                    buffer.flip();
                    String request = new String(buffer.array(), 0, buffer.limit());
                    System.out.println("Request: " + request);
                    
                    // 응답 전송
                    buffer.clear();
                    buffer.put("HTTP/1.1 200 OK\r\n\r\nHello NIO!".getBytes());
                    buffer.flip();
                    client.write(buffer);
                }
            }
        }
    }
}
```

<br>

## NIO와 BIO 비교
| 항목 | BIO (Blocking I/O) | NIO (Non-blocking I/O) |
| --- | --- | --- |
| **스레드 모델** | 하나의 요청당 하나의 스레드 | 하나의 스레드가 여러 요청 처리 |
| **성능** | 클라이언트 증가 시 성능 저하 | 많은 클라이언트 연결을 효율적으로 처리 |
| **자원 사용** | 스레드 낭비 (빈 연결도 스레드 점유) | 자원 절약 (스레드는 준비된 요청만 처리) |
| **복잡성** | 구현이 간단 | Selector와 Channel 관리 필요 |
| **확장성** | 낮음 | 높음 |

<br>

## 결론
- NIO는 **대규모 시스템**에서 **높은 성능과 동시성 처리**를 가능하게 합니다.
- BIO의 단점을 해결하고 **적은 스레드로 많은 클라이언트를 처리**할 수 있어 확장성이 뛰어납니다.
- 현재 대부분의 웹 서버는 NIO를 기본으로 사용하거나, NIO 기반의 개선된 **Asynchronous I/O (AIO)** 방식을 채택하고 있습니다.

## 정리
- 톰캣이란 OS로부터 TCP 연결(소켓통신)을 맺고 클라이언트로부터 패킷을 받아 자바 객체로 만드는 역할을 담당하는 미들웨어이다.
- I/O에는 file, pipe, socket, device 등이 존재하며 이들은 FD(파일 디스크립터)를 기반으로 동작한다.
- 유저 레벨의 프로세스가 I/O를 하기 위해선 OS의 도움. 즉, 시스템 콜이 필요하다.
- OS에 요청한 시스템 콜을 '기다릴 것이냐/그렇지 않을 것이냐'에 따라 Blocking I/O, Non-Blocking I/O로 구분된다.
- 또한 OS로부터의 응답을 '요청한 task가 받아 처리할 것이냐/다른 task가 받아 처리할 것이냐'에 따라 Synchronous/Asynchronous로 구분된다.
- Multiplexing이란 하나의 시스템 콜이 하나의 소켓의 버퍼에 데이터를 기다리는 것이 아닌, 하나의 시스템 콜을 통해 여러 개의 소켓으로부터 I/O 이벤트를 요청받을 수 있는 방식이다.
- 프로세스의 작업 처리를 워크로드라 하며 CPU 기반의 워크로드와 I/O 기반의 워크로드로 구분된다.
- CPU 기반의 워크로드는 유저 프로세스가 직접 CPU에 할당되어 작업을 처리하기 때문에 컨텍스트 스위칭이 필요 없어 I/O 기반의 워크로드보다 빠르다. 또한 I/O 기반의 워크로드는 디바이스에 접근하는 시간이 소요되기 때문에 CPU 기반의 워크로드보다 느리다.
- 프로세스의 워크로드 특성을 파악하고 Blocking I/O, Non-Blocking I/O를 결정하는 것이 좋다.

<br>

## Tomcat vs Netty
- Tomcat: 주로 서블릿 컨테이너로 사용됩니다. HTTP 서버로서 HTTP 요청을 받아 처리하는 방식입니다. 여러 스레드를 사용하여 요청을 처리하며, 요청 처리 후 응답을 반환합니다.
- Netty: 비동기 이벤트 기반 네트워크 애플리케이션 프레임워크로, HTTP 외에도 TCP, UDP 등 다양한 프로토콜을 지원합니다. 고성능을 요구하는 네트워크 통신 애플리케이션에서 널리 사용됩니다. 비동기, 논블로킹 방식으로 작동하여 더 효율적으로 리소스를 사용할 수 있습니다.

<br>

## 요청 Timeout 처리 (Handshaking 되었으면 응답 받지 말고 정상 종료? Handshaking 과정 전에 타임 아웃 처리했으면 패킷 버리기?)
- Handshaking 완료 후 응답 받지 말고 정상 종료: 일반적으로 손쉬운 방법은 클라이언트와 서버 간에 핸드쉐이킹이 완료된 후, 타임아웃을 설정하고 요청을 마무리하는 것
- Handshaking 전 타임아웃 처리 후 패킷 버리기: 핸드쉐이킹이 완료되지 않은 상태에서 타임아웃이 발생하면, 연결을 강제로 종료하거나 연결을 닫는 것이 일반적

<br>

## 나의 서버 (Tomcat 스레드 100개), 상대방 서버 (Tomcat 스레드 20개)의 스레드 개수 다르면 어떻게 처리되는가?
- 스레드 개수가 더 많은 곳이 더 많은 동시 요청을 처리할 수 있음. 

<br>

## Apache -> JVM에 존재하는가 (WAS로 묶여있는가)?
- WAS : Tomcat은 JVM 내에서 실행
- Apache : JVM에 존재x 
