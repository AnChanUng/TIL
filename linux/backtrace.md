## 백트레이스(backstrace)
프로그램 실행 중 특정 시점에서 호출된 함수들의 역순 목록을 제공하여, 에러 발생 시점까지의 함수 호출 경로를 추적하는 데 사용됩니다. 

이를 통해 개발자는 프로그램이 어떤 경로를 거쳐 에러에 도달했는지 파악하여 디버깅을 보다 효율적으로 수행

C 언어에서는 이러한 백트레이스를 직접 지원하지 않지만, Linux 환경에서는 execinfo.h 헤더에 정의된 backtrace 및 backtrace_symbols 함수를 활용하여 호출 스택 정보를 추출할 수 있습니다. 

https://man7.org/linux/man-pages/man3/backtrace.3.html
