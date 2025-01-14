## 운영체제
컴퓨터 하드웨어 바로 위에 설치되어 사용자 및 다른 모든 소프트웨어와 하드웨어를 연결하는 소프트웨어 계층으로 모든 컴퓨터 시스템의 필수적인 부분이다.

> 컴퓨터를 부팅하고 난 이후 메모리에 상주하는 운영체제 부분을 커널이라고 한다.

## 운영체제의 목적
컴퓨터 시스템을 편리하게 사용할 수 있는 환경 제공
컴퓨터 시스템의 자원을 효율적으로 관리

운영체제는 **사용자가 컴퓨터와 상호작용**할 수 있도록 일종의 인터페이스 역할을 수행합니다.
예를 들어, 시작메뉴, 바탕화면, 창 등을 통해 사용자가 프로그램을 실행하거나 파일 관리를 할 수 있게 해주는 것(GUI)

> 하드웨어를 직접 다루는 복잡한 부분은 운영체제가 대행해 주고, 각 사용자나 프로그램은 그에 대한 자세한 내용을 알지 못하더라도 프로그램을 실행할 수 있도록 해주는 것입니다.

## 운영체제의 기능
![image](https://github.com/user-attachments/assets/a0b0da38-3aa3-4df5-898a-9b74fe3ef5a5)
**CPU 스케줄링** - 다중 프로세스 환경에서 프로세스들이 CPU를 효율적으로 사용할 수 있도록 실행 순서를 정하는 작업. ex) FCFS, SJF, Round Robin
**인터럽트** (Interrupt) - CPU가 현재 작업을 수행하던 중, 외부 또는 내부 이벤트에 의해 작업을 중단하고 다른 작업(이벤트 처리)을 수행하도록 하는 신호
**디스크 스케줄링 -** 디스크 I/O 요청 처리 순서를 최적화하여 디스크 접근 시간을 줄이는 기법

## CPU 스케쥴링
Ready 상태의 프로세스 중에서 어떤 프로세스에게 CPU를 할당할지 결정합니다. 이를 CPU 스케줄링 이라고 합니다. 또한 Dispatcher는 CPU 제어권을 CPU 스케줄러에 의해 선택된 프로세스에게 넘깁니다. 이를 Context Switch라고 합니다.

## 운영체제 종류
Windows, Mac OS, Linux, Unix

## 운영체제의 분류

- 단일 작업(single tasking)
    - 한 번에 하나의 작업만 처리
        
        ex) MS-DOS 프롬포트 상에서는 한 명령의 수행을 끝내기 전에 다른 명령을 수행시킬 수 없음.
        
- 다중 작업(multi tasking)
    - 동시에 두 개 이상의 작업 처리
        
        ex) UNIX, MS Windows 등에서는 한 명령의 수행이 끝나기 전에 다른 명령이나 프로그램을 수행할 수 있음.
        

## 사용자의 수

단일 사용자(single user) ex) MS-DOS, MS Windows

다중 사용자(multi user) ex) UNIX, NT server

## 처리 방식

- 일괄 처리(batch processing)
    - 작업 요청의 일정량 모아서 한꺼번에 처리
    - 작업이 완전 종료될 때까지 기다려야 ex) 초기 Punch Card 처리 시스템
- 시분할(time sharing)
    - 여러 작업을 수행할 때 컴퓨터 처리 능력을 일정한 시간 단위로 분할하여 사용
    - 일괄 처리 시스템에 비해 짧은 응답 시간을 가짐 ex) UNIX
    - interactive한 방식
- 실시간(Realtime OS)
    - 정해진 시간 안에 어떠한 일이 반드시 종료됨이 보장되어야 하는 실시간 시스템을 위한 OS
    - 예) 원자로 / 공장 제어, 미사일 제어, 반도체 장비, 로보트 제어
- 실시간 시스템의 개념 확장
    - Hard realtime system(경성 실시간 시스템)
    - Soft realtime system(연성 실시간 시스템)

## 용어 정리

Multitasking : 여러 작업이 동시에 실행되는 것

Multiprogramming : 메모리에 여러 프로그램이 동시에 올라가 있음

Time sharing : 시간을 공유해서 쓰는 것. 

Multiprocess : 여러 프로그램이 동시에 실행된다.

Multiprocessor : 하나의 컴퓨터에 CPU (processor)가 여러 개 붙어 있음을 의미

- 위의 용어들은 컴퓨터에서 여러 작업을 동시에 수행하는 것을 뜻한다.
- Multiprogramming은 여러 프로그램이 메모리에 올라가 있음을 강조
- Time Sharing은 CPU의 시간을 분할하여 나누어 쓴다는 의미를 강조

## 운영 체제의 예

- 유닉스(UNIX)
    - 코드의 대부분을 C언어로 작성
    - 높은 이식성
    - 최소한의 커널 구조
    - 복잡한 시스템에 맞게 확장 용이
    - 소스 코드 공개
    - 프로그램 개발에 용이
    - 다양한 버전
        - System V, FreeBSD, SunOS, Solaris
        - Linux
- DOS(Disk Operating System)
    - MS사에서 1981년 IBM-PC를 위해 개발
    - 단일 사용자용 운영체제, 메모리 관리 능력의 한계(주 기억 장치 : 640KB)
- MS Windows
    - MS사의 다중 작업용 GUI 기반 운영 체제
    - Plug and Play, 네트워크 환경 강화
    - DOS용 응용프로그램과 호환성 제공
    - 불안정성
    - 풍부한 지원 소프트웨어
- Handheld device를 위한 OS
    - PalmOS, Pocket PC(WinCE), Tiny OS
