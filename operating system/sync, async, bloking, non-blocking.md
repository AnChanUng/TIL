### 동기화(Synchronous)
동기화는 여러 프로세스나 스레드가 공유 자원에 접근할 때 상호 배제(Mutual Exclusion)를 보장하는 과정
> 즉, 여러 스레드가 동시에 같은 자원에 접근할 경우 경쟁 조건(race condition)이 발생하지 않도록 보장합니다.

동기화(Synchronization)은 여러개의 스레드 또는 프로세스가 공유 자원을 접근할 때, 서로의 작업이 서로의 영향을 미치지 않도록 조절하는 것을 말한다. 동기화를 통해 공유 자원에 대한 접근을 제한함으로써, 스레드 또는 프로세스 간의 경쟁상황이 발생하지 않도록 하여 일관성 있는 결과를 보장할 수 있게된다.

ex) 뮤텍스, 세마포어

C언어 scanf()
Java Scanner 객체의 next() 메서드 같이 사용자의 입력을 받는 함수들

### 비동기화(Asynchronization)
여러 작업을 동시에 처리하면서, 작업이 완료되기 전에 다른 작업을 수행할 수 있는 것을 말한다. 즉, 비동기화는 작업을 순차적으로 처리하는 동기화와 달리, 작업을 동시에 처리할 수 있어 처리 속도를 향상 시킬 수 있다.

ex) 비동기식 I/O와 callback


동기화: 한 작업이 끝나고 나서야 다음 작업을 시작하는 방식. 즉, 순차적으로 처리됩니다.
비동기화: 작업을 요청한 후, 그 작업이 완료될 때까지 기다리지 않고 다른 작업을 계속 진행하는 방식. 동시에 여러 작업을 처리할 수 있습니다.

js의 setTimeout()함수
```
setTimeout(foo, 3000);

function foo() {
    console.log("2");
}
console.log("1");
```
setTimeout() 함수를 호출하면 3초 뒤에 foo() 함수가 수행되는 코드입니다.
setTimeout() 함수를 호출한 후 3초라는 시간이 흘러야 foo() 함수가 수행되며, 이 시간 동안 프로그램은 다음 코드를 읽기 때문에, 위 코드는 콘솔에 1, 2 순서로 출력이 됩니다.

여기서 foo()함수를 setTimeout()함수의 콜백( callback )함수라고 합니다.
콜백 함수란 비동기 방식에서 어떤 수행이 완료되었을 때( event가 발생했을 때 ) 수행해야 할 함수를 의미합니다.
이처럼 비동기 방식에서는 함수를 호출한 쪽에서 수행 결과를 직접 처리하지 않고 콜백 함수를 통해 수행 결과를 처리합니다.



### Blocking
자신의 수행결과가 끝날 때까지 제어권을 갖고 있는 것을 의미

### non-blocking
자신이 호출되었을 때 제어권을 바로 자신을 호출한 쪽으로 넘기며, 자신을 호출한 쪽에서 다른 일을 할 수 있도록 하는 것을 의미


### 둘은 비슷한거 아닐까?
Blocking, Non-Blocking의 기준은 제어권을 어느 쪽이 가지고 있느냐로 나뉘어지지만, Sychronous, Asynchronous 는 작업의 반환에 대해 영향을 받는지 받지 않는지로 나뉘어집니다.


### 동기/비동기 + 블로킹/논블로킹 조합
1. Sync Blocking (동기 + 블로킹) :  다른 작업이 진행되는 동안 자신의 작업을 처리하지 않고 (Blocking), 다른 작업의 완료 여부를 바로 받아 순차적으로 처리하는 (Sync) 방식
2. Async Blocking (비동기 + 블로킹)
3. Sync Non-Blocking (동기 + 논블로킹) : 다른 작업이 진행되는 동안에도 자신의 작업을 처리하고 (Non Blocking), 다른 작업의 결과를 바로 처리하여 작업을 순차대로 수행 하는 (Sync) 방식
4. Async Non-Blocking (비동기 + 논블로킹) : 다른 작업이 진행되는 동안에도 자신의 작업을 처리하고 (Non Blocking), 다른 작업의 결과를 바로 처리하지 않아 작업 순서가 지켜지지 않는 (Async) 방식 
