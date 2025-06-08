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
