### 파드 조회
```
kubectl get pods
```

### 파드 포트 포워딩
```
kubectl port-forward pod/[파드명] 80:80
```
### 파트 삭제
```
kubectl delete pod [파드명]
kubectl delete all -all
```

### 매니페스트 파일에 적혀져있는 리소스(파드 등) 생성
```
kubectl apply -f nginx-pod.yaml
```

## 파드 디버깅

### 에러 메시지 확인
```
kubectl describe pods [파드명]
```

### 파드 로그 확인
```
kubectl logs [파드명]
```

### 파드 접속
```
kubectl exec -it [파드명] -- bash
```

## Deployment 관련 명령어

### Deployment 조회
```
kubectl get deployment
```
### Deployment 삭제
```
kubectl delete deployment spring-deployment
```
## Service 관련 명령어

### Service 조회
```
kubectl get service
```
### Service 삭제
```
kubectl delete service spring-service
```
