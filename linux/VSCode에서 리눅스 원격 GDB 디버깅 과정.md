## VSCode에서 리눅스 원격 GDB 디버깅 과정
> GDB(GNU Debugger)는 C/C++ 프로그램의 디버깅을 위한 도구

### 1. launch.json 설정
program: 실행할 바이너리 경로(./process)
port: gdbserver에서 사용할 포트(예: 4567)
### 2. 원격 서버에서 gdbserver 실행, 리눅스 서버(원격 터미널)에서 실행
```
gdbserver :4567 ./process
```
> 출력
```
Process ./process created; pid = 12345
Listening on port 4567
```
> GDB 서버가 실행되고 대기 상태

### 3. VSCode에서 Breakpoint 설정
디버깅할 process.c 파일에서 중단점(Breakpoint) 설정
중단점 걸고 싶은 줄 클릭 후 왼쪽 클릭 (빨간 점 표시)
### 4. VSCode에서 디버깅 시작 (F5)
F5(Start Debugging) 버튼 클릭
VSCode가 원격 gdbserver에 연결
실행이 Breakpoint에서 멈춤 → 디버깅 가능! 


디버깅 중 주요 명령어 (VSCode Debug Console 또는 터미널 사용 가능)
```
continue   # 프로그램 계속 실행
next       # 다음 줄 실행
step       # 함수 내부로 들어가기
print var  # 변수 값 출력
bt         # 백트레이스(콜 스택 보기)
info registers  # CPU 레지스터 확인
```
### 디버깅 종료
Ctrl + C → 실행 중인 gdbserver 중지
detach → 디버거 연결 해제 후 종료

### 요약
1. launch.json에서 program, 포트번호 수정 <br>
2. 리눅스에서 gdbserver :포트번호 ./process 실행 <br>
3. VSCode에서 Breakpoint 설정 <br>
4. F5(Start Debugging) 실행 → 디버깅 시작
