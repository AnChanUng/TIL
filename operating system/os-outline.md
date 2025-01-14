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