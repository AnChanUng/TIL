## 버블 정렬
서로 인접해 있는 요소 간의 대소 비교를 통해 정렬
> 시간 복잡도 O(n^2) 공간 복잡도 O(n)
```
int* answer = (int*)malloc(num_list_len*sizeof(int));
int tmp;
for(int i=0; i<num_list_len; i++) {
    for(int j=i+1; j<num_list_len; j++) {
        if(num_list[i] > num_list[j]) {
            tmp = num_list[i];
            num_list[i] = num_list[j];
            num_list[j] = tmp;
        }
    }
}
```
## 삽입 정렬
정렬을 진행할 원소의 index보다 낮은 곳에 있는 원소들을 탐색하며 알맞은 위치에 삽입해주는 정렬 알고리즘
> 시간 복잡도 O(n) 공간 복잡도 O(n)

## 선택 정렬
배열에서 최소값을 반복적으로 찾아 정렬하는 알고리즘
> 시간 복잡도 O(n^2)

## 퀵 정렬
분할정복법과 재귀를 사용해 정렬하는 알고리즘
> pivot: 정렬 될 기준 원소

## 병합 정렬
분할정복과 재귀 알고리즘을 사용하는 정렬 알고리즘

## 힙 정렬
힙이란 트리 기반의 자료구조로서, 두 개의 노드를 가진 완전 이진 트리를 의미 <br>
힙 정렬이란 완전 이진 트리를 기반으로 하는 정렬 알고리즘

## 셸 정렬 (shell sort)
삽입 정렬의 단점을 보완하고자 도입

## 기수 정렬 (Radix Sort)
non-comparison 알고리즘으로 원소간의 대소 비교를 하지 않고 정렬하는 알고리즘

## 카운팅 정렬 (Counting Sort)
카운팅 정렬(계수 정렬)은 non-comparison sort 알고리즘에 해당하는 알고리즘

## Tim Sort
삽입정렬과 병합정렬이 결합된 정렬 알고리즘
> 시간 복잡도 최선: O(n) 최악: O(nlogn) 
![image](https://github.com/user-attachments/assets/5f7f967b-cb16-4866-a885-3703ad8f007b)

