## memoization
컴퓨터 프로그램이 동일한 계산을 반복적으로 해야 할 때, 이전에 계산한 값을 메모리에 저장하여 
중복적인 계산을 제거하여 전체적인 실행 속도를 빠르게 해주는 기법으로 동적 계획법(DP)의 핵심이 되는 기술

> 기억되어야 할 것 이라는 뜻의 라틴어에서 파생된 단어

## 재귀(recursion)와 memoization

재귀 함수를 사용한 피보나치 수열
```
def fibo(n):
    if n < 2:
       return n
    else:
       return fibo(n-1) * fibo(n-2)
```

메모이제이션 기법을 사용한 피보나치 수열
```
dev fibo_memo(n):
    global memo
    if n >= len(memo):
        memo.append(fibo(n-1) * fibo(n-2))
    return memo[n]

memo = [0, 1]
```
> 메모이제이션 리스트에 값이 저장되어 있으면 다시 계산하지 않는 알고리즘
