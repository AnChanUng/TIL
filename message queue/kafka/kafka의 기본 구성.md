### Topoic, Consumer, Producer
<img width="924" height="321" alt="image" src="https://github.com/user-attachments/assets/401d11a2-f5aa-4f4f-969c-76c5c8a5b0e2" />

- Producer : kafka에 message(data)를 전달하는 주체
- Consumer : kafka의 message(data)를 처리하는 주체
- Topic : kafka에 넣을 메시지의 종류를 구분하는 개념 (카테고리와 유사)

> Produer는 Kafka의 message를 전달하고, kafka는 message queue에 topic별로 구분해 전달받은 메시지를 저장한다. 
Consumer는 Kafka에 새로운 메시지가 생겼는지 주기적으로 체크 후, 새로운 메시지가 있다는걸 발견하면 그 메시지를 조회해서 처리한다.

### Topic 생성 / 조회 / 삭제
- CLI를 활용한 Kafka 조작
<img width="884" height="448" alt="image" src="https://github.com/user-attachments/assets/9647903b-54b6-4022-b196-8f9564024f62" />

### Topic 관련 명령어
topic 생성
```
cd kafka 디렉토리
bin/kafka-tipics.sh \
-- bootstrap-server localhost:9092 \
-- create \
-- topic email.send
```
topic 조회
```
# topic 전체 조회
bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--list

# 특정 토픽 세부 정보 조회
bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--describe --topic email.send
```
topic 삭제
```
# 토픽 삭제
# bin/kafka-topics.sh --bootstrap-server <kafka 주소> --delete --topic <토픽명>
$ bin/kafka-topics.sh \
	--bootstrap-server localhost:9092 \
	--delete --topic email.send

# 잘 삭제됐는 지 확인하기
$ bin/kafka-topics.sh \
	--bootstrap-server localhost:9092 \
	--list
```

### kafka 로드맵
> 1. AWS EC2에 kafka 설치 / 실행
> 2. Kafka 기본 개념
> 3. Kafka 메시지 처리 실패 시 대처 방법
> 4. Kafka 메시지 처리 성능 높이기 (병렬처리)
> 5. Kafka 장애 대비하기 (고가용성)
> 6. MSA 프로젝트에서 Kafka 도입하기

### 출처
https://www.inflearn.com/courses/lecture?courseId=338153&tab=curriculum&type=LECTURE&unitId=327416
