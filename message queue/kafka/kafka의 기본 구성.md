### Topoic, Consumer, Producer
<img width="924" height="321" alt="image" src="https://github.com/user-attachments/assets/401d11a2-f5aa-4f4f-969c-76c5c8a5b0e2" />

- Producer : kafka에 message(data)를 전달하는 주체
- Consumer : kafka의 message(data)를 처리하는 주체
- Topic : kafka에 넣을 메시지의 종류를 구분하는 개념 (카테고리와 유사)

> Producer는 Kafka의 message를 전달하고, kafka는 message queue에 topic별로 구분해 전달받은 메시지를 저장한다. 
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

### 명령어 정리
특정 토픽에 쌓인 메시지 전부 조회하기 + 실시간으로 메시지 조회하기
```
$ bin/kafka-console-consumer.sh \
	--bootstrap-server localhost:9092 \
	--topic email.send \
	--from-beginning
```
- --from-beginning : 토픽에 저장된 가장 처음 메시지부터 출력해온다.

### Consumer Group, Offset
메시지를 어디까지 읽었는 지 기억하고, 그 다음 메시지부터 처리하기
- Kafka에서 Consumer Group 이라는 개념을 활용하면, 각 Consumer Group이 어디까지 메시지를 읽었는지를 offset이라는 번호로 기록해둔다. 이 덕분에 컨슈머 Consumer Group에 속해있는 Consumer들은 안 읽은 메시지부터 순차적으로 메시지를 읽게 된다.

- Consumer : 카프카의 메시지를 처리하는 주체
- Consumer Group : 1개 이상의 컨슈머를 하나의 그룹으로 묶은 단위
- offset : 메시지의 순서를 나타내는 고유 번호 (0부터 시작)
<img width="938" height="395" alt="image" src="https://github.com/user-attachments/assets/5714b3d2-817d-484a-9cc8-b2210356b96b" />
- 토픽에 저장되어 있는 여러 메시지는 메시지의 순서를 나타내는 고유 번호인 오프셋(offset)을 가지고 있다.
- 오프셋(offset) 번호는 인덱스처럼 0부터 시작한다.
- Consumer Group은 1개 이상의 Consumer를 가질 수 있다.
- Consumer Group은 어디까지 메시지를 읽었는 지에 대한 정보(CURRENT-OFFSET)를 알고 있다.
  - CURRENT-OFFSET : 다음에 읽을 메시지의 오프셋 번호



### 출처
https://www.inflearn.com/courses/lecture?courseId=338153&tab=curriculum&type=LECTURE&unitId=327416
