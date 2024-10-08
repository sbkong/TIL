[How to sort arrays in Java and avoid TLE](https://codeforces.com/blog/entry/7108)

**Abstract**

Below I will describe how to avoid TLE (timelimit exceeded) when sorting an array in Java. I will describe two methods.

**Introduction**

When trying to sort an array in Java it is convenient to use Arrays.sort():

```
long[] arr = {5,3,4,2,1};
Arrays.sort(arr);
// after sorting print arr
```

However, this method uses quicksort if the array contains elements of primitive datatype such as long or int. Quicksort has on average a runtime of ![](https://espresso.codeforces.com/8be993eafecdfb87ed795da91da8626cc7b54983.png), but keep in mind that the worst-case runtime is _O_(_n_2) for arrays that are almost sorted. As a consequence you can get a TLE (timelmit exceeded) by the online judge. Below are two methods that avoid this issue.

**Method 1: Use objects (wrapper class)**

We will use the wrapper class `Long` of the primitive datatype `long`. Given an array `long[] arr` we will introdue an array `Long[] arr_obj`. While `long[] arr` is sorted with quicksort `Long[] arr_obj` is sorted with mergesort which has a worst-case runtime of ![](https://espresso.codeforces.com/8be993eafecdfb87ed795da91da8626cc7b54983.png). In Java an array with objects is sorted with mergesort when using Arrays.sort().

```
long[] arr = {5,3,4,2,1};
int n = arr.length;
Long[] arr_obj = new Long[n];
for(int i=0; i<n; ++i){
   arr_obj[i] = new Long(arr[i]);
}
Arrays.sort(arr_obj);
// after sorting print arr_obj
```

**Method 2: shuffling the array before quicksort**

We can still use quicksort but in order to avoid the worst-case runtime of _O_(_n_2) for an almost sorted array we will first shuffle it and then apply quicksort.

```
long[] arr = {1,2,3,5,4};
shuffleArray(arr);
Arrays.sort(arr);
// after sorting print arr
```

The function shuffleArray() is given by

```
void shuffleArray(long[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            long tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
}
```

**References**

[1] [More On Shuffling An Array Correctly]([http://blog.ryanrampersad.com/2012/03/more-on-shuffling-an-array-correctly/](http://blog.ryanrampersad.com/2012/03/more-on-shuffling-an-array-correctly/)) – blog post by Ryan Rampersad.

[2] [Why java Arrays use two different sort algorithms for different types?]([http://stackoverflow.com/questions/3707190/why-java-arrays-use-two-different-sort-algorithms-for-different-types](http://stackoverflow.com/questions/3707190/why-java-arrays-use-two-different-sort-algorithms-for-different-types)) (discussion on stackoverflow.com)

[3] Java doc on [quicksort](http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(long[])) and [mergesort](http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(java.lang.Object[])).

# https://www.acmicpc.net/problem/2751

1. **O(N^2)짜리 정렬 알고리즘을 사용하면 당연히 시간 초과입니다.** 이에 해당하는 것으로는 **버블 정렬, 선택 정렬, 삽입 정렬** 등이 있습니다. 이 문제는 O(NlogN) 이하의 복잡도를 갖는 정렬을 사용해야 하고, 이에 해당하는 것으로는 **병합 정렬, 힙 정렬** 등이 있습니다. 또는 기수 정렬이나 카운팅 정렬을 사용해도 됩니다.
2. **퀵 정렬은 최악의 경우 O(N^2)입니다.** 이름에 quick이 있다고 속으면 안 됩니다. 평균 시간복잡도는 O(NlogN)이지만, 평범하게 구현한 퀵 정렬은 매우 단순한 방법으로 최악의 케이스의 시간복잡도인 O(N^2)을 만들 수 있습니다. **단순히 이미 정렬이나 역정렬된 상태로만 입력이 주어져도 퀵 정렬이 감당할 수 없습니다.**
    1. 이를 회피하는 방법으로 피벗으로 중앙값의 중앙값 고르기, 재귀가 깊어지면 다른 정렬을 사용하기, 랜덤으로 섞은 뒤에 수행하기 등이 있지만 **정말 잘 구현하지 않으면** 여전히 효율이 매우 안 좋습니다.
    2. 그래서 퀵 정렬은 그냥 이 문제에 사용하지 않기를 권장합니다. 이 문제 뿐만 아니라 **어떤 알고리즘 문제에도 사용하지 않는 것이 좋습니다.** 연습하기 위한 목적으로만 쓰세요. 이 문제에서는 수가 중복되지 않기 때문에 랜덤으로 섞기나 피벗을 가운데에 두는 것 등은 비교적 잘 되기는 하지만, 굳이 안전하고 어디서든 쓸 수 있는 방법들을 놔두고 위험한 길을 택할 필요는 없습니다. 개인적으로는 직접 구현할 거라면 병합 정렬이 가장 쉽고 디버깅하기도 용이하다고 생각합니다.
3. 병합 정렬을 할 때, merge를 수행할 때마다 배열의 전체 크기, 혹은 right만큼을 할당하고 해제하기를 반복하면 안 됩니다. 크기가 N인 메모리를 할당하는 것은 O(N) 시간이 걸리고, merge가 O(N)번 호출되기 때문에 총 시간복잡도가 O(N^2)이 됩니다. 이를 해결하기 위한 방법으로는,
    1. 복사를 하기 위한 큰 배열 하나를 미리 할당해두고 계속 사용
    2. merge가 담당하는 구간의 크기(right - left + 1)만큼만 메모리를 할당 등이 있습니다.
4. 힙 정렬은 복잡한 편입니다. **힙 정렬이 요구하는 것이 무엇인지 정확하게 알고 사용해야 합니다.** 힙 정렬에서 시간 초과가 났다면 힙 정렬에서 해야 할 일을 정확하게 하지 않고 비효율적인 연산들을 했을 가능성이 매우 높습니다.
    1. 대표적으로, 원소 하나를 넣거나 뺄 마다 전체 힙을 재구성하면 안 됩니다. 해당 원소가 들어간 자리 / 빠진 자리를 처리하기 위해 O(logN)번을 넘는 연산이 있으면 안 됩니다.
5. 출력할 때 endl을 쓰지 마세요. endl은 버퍼를 flush 하는 역할을 겸하기 때문에 **매우 매우 매우 매우** 느립니다. '\n'을 대신 사용하세요. 이 문제뿐만 아니라 모든 문제에 해당됩니다. [https://www.acmicpc.net/proble...](https://www.acmicpc.net/problem/15552) 를 참고하세요.
6. **원소의 수는 최대 100만 개입니다. 배열의 크기를 충분하게 잡았는지 확인하세요.  
   **
7. 입력되는 정수의 범위는 **절댓값**이 100만 이하입니다. 즉, **마이너스 100만**에서 플러스 100만까지입니다. 카운팅 정렬을 한다면 조심하세요.
8. Python은 매우 느립니다. 내장 정렬 함수를 사용하면 괜찮지만, 직접 정렬을 구현해서는 시간 보너스를 감안하더라도 통과하기가 매우 힘듭니다. PyPy2나 PyPy3로 제출해 보세요.
9. Java에서 Arrays.sort에 primitive type array를 전달하면 dual-pivot quicksort를 수행하기 때문에 최악의 경우 O(N^2)이 됩니다. 이는 보통의 방법으로는 웬만해서는 O(N^2)이 안 되지만 이 문제에는 [https://www.acmicpc.net/board/view/34491](https://www.acmicpc.net/board/view/34491) 에 의해 추가된 저격 데이터가 있습니다. Collections.sort를 사용하는 편이 좋습니다.

___
다른 질문글들을 참조해서

Buffer에 다 쓴 후에 한번에 플러시하는 코드로 바꿨는데도 시간초과(30% 즘)가 뜹니다.

StringBuilder 를 써봐도 똑같이 시간초과가 뜨는데,

정렬알고리즘을 표준라이브러리가 아닌 직접 작성해야지만 통과할 수 있는 문제인건가요?

```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int countCase = Integer.parseInt(br.readLine());
    int[] arr = new int[countCase];

    for (int i = 0; i < countCase; i++) {
      int a = Integer.parseInt(br.readLine());
      arr[i] = a;
    }

    Arrays.sort(arr);

    for (int i : arr) {
      bw.write(i + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
```
