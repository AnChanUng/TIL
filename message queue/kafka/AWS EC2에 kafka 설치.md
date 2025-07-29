### AWS EC2에 kafka 설치 / 실행

1. jdk 17 설치
```
$ sudo apt update
$ sudo apt install openjdk-17-jdk
$ java -version
```

2. kafka 설치 파일 다운 / 압축 해제
```
$ wget https://dlcdn.apache.org/kafka/4.0.0/kafka_2.13-4.0.0.tgz
$ tar -xzf kafka_2.13-4.0.0.tgz
```

3. kafka를 실행시켰을 때 잡아먹는 메모리 크기 낮추기
```
$ export KAFKA_HEAP_OPTS="-Xmx400m -Xms400m"
```
  
4. swap을 활용해 메모리 늘리기
4.1 swap 메모리를 2GB로 설정
```
$ sudo dd if=/dev/zero of=/swapfile bs=128M count=16 # 2GB짜리의 파일 생성
$ sudo chmod 600 /swapfile # 파일에 권한 부여
$ sudo mkswap /swapfile # 2GB 짜리 파일을 swap 공간의 형태로 전환
$ sudo swapon /swapfile # swap 활성화
```
4.1 swap 메모리를 2GB로 설정
```
$ sudo vi /etc/fstab
/swapfile swap swap defaults 0 0
```
4.2. 시스템 부팅 시마다 자동으로 활성화 되도록 파일시스템 수정
```
# 시스템 부팅 시마다 자동으로 활성화 되도록 파일시스템 수정
$ sudo vi /etc/fstab
# 아래 내용을 추가하고 저장하기
/swapfile swap swap defaults 0 0
```
4.3. swap 메모리가 잘 설정됐는 지 확인
```
$ free
```
5 Kafka 설정 수정
```
$ vi config/server.properties
# 외부에서 접근할 때 사용하는 주소
advertised.listeners=PLAINTEXT://{EC2 Public IP}:9092,CONTROLLER://{EC2 Public IP}:9093
```
6. Kafka 서버 시작
```
# 초기 로그 폴더 셋팅 (카프카 첫 실행 때만 이 명령어를 입력하면 됨)
$ KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
$ bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties

# 카프카 서버 실행
$ cd ~/kafka_2.13-4.0.0
$ bin/kafka-server-start.sh config/server.properties # 포그라운드에서 실행
```
8. Kafka 서버를 백그라운드에서 실행
```
# 백그라운드에서 실행
$ bin/kafka-server-start.sh -daemon config/server.properties 

# 백그라운드에서 실행되고 있는 kafka 서버의 로그 확인하기
$ tail -f logs/kafkaServer.out 
```
9. Kafka 서버가 실행 중인지 확인하는 방법
```
$ sudo lsof -i:9092 # 9092번 포트에서 실행 중인 프로세스 조회
```
