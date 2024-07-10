2024년 7월 10일 
문제를 푸는데 Collection과 HashMap을 사용하는 예제가 나왔다.

그래서 이론 공부를 해보려고 한다.


# HashSet
- `HashSet`은 자바에서 `Set` 인터페이스를 구현한 클래스입니다. 해시 테이블을 사용하여 요소를 저장하며, 요소 추가, 제거, 포함 여부 확인 등의 기본적인 작업을 상수 시간 안에 수행할 수 있습니다 (해시 함수가 요소를 적절히 분산시키는 경우).

### 특징
- 고유한 요소(중복 요소 불가능)
  중복 값 추가 시 false return
- 무작위 삽입 순서
  삽입 순서를 유지하려면 LinkedHashSet을 사용해야 함
- 연산 속도가 빠르다.
    - **주의 : 해시 충돌이 발생할 경우 성능 저하

### e.g.
```Java
import java.util.HashSet;

public class HashSetExample {

    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();

        // 요소 추가
        hashSet.add("사과");
        hashSet.add("바나나");
        hashSet.add("딸기");

        // 중복된 요소 추가 시도
        boolean added = hashSet.add("사과"); // 이미 존재하므로 false 반환

        // 요소 출력
        System.out.println("HashSet의 요소: " + hashSet); // 출력 순서는 무작위

        // 요소 포함 여부 확인
        boolean containsBanana = hashSet.contains("바나나");
        System.out.println("바나나 포함 여부: " + containsBanana);

        // 요소 개수 확인
        int size = hashSet.size();
        System.out.println("HashSet의 요소 개수: " + size);
    }
}

```

# Collection
- `Collection`은 자바에서 모든 컬렉션 인터페이스의 최상위 인터페이스입니다. `List`, `Set`, `Queue` 및 다른 컬렉션들이 이 인터페이스를 구현하여 여러 데이터 요소들을 그룹화하고 조작할 수 있습니다.

### 특징
- **그룹화**: `Collection` 인터페이스를 구현한 클래스들은 데이터 요소들을 그룹화하고 관리할 수 있습니다. 예를 들어, `List`는 순서가 있는 요소들을 저장하며, `Set`은 고유한 요소들을 저장합니다.

- **메서드**: `Collection` 인터페이스는 요소들을 추가, 제거, 검색하는 다양한 메서드를 제공합니다. 예를 들어, `add()`, `remove()`, `contains()` 등의 메서드를 통해 요소를 관리할 수 있습니다.

- **다양한 구현체**: 자바에서는 `Collection` 인터페이스를 구현한 다양한 클래스들이 제공됩니다. 각 클래스는 특정한 요구사항에 맞게 데이터를 저장하고 관리할 수 있도록 설계되어 있습니다.
### e.g.
```Java
import java.util.ArrayList;
import java.util.Collection;

public class CollectionExample {

    public static void main(String[] args) {
        Collection<String> fruits = new ArrayList<>();

        // 요소 추가
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("딸기");

        // 중복된 요소 추가 시도
        boolean added = fruits.add("사과"); // 이미 존재하므로 false 반환

        // 요소 출력
        System.out.println("Collection의 요소: " + fruits); // 순서는 삽입 순서대로 출력됨

        // 요소 포함 여부 확인
        boolean containsBanana = fruits.contains("바나나");
        System.out.println("바나나 포함 여부: " + containsBanana);

        // 요소 개수 확인
        int size = fruits.size();
        System.out.println("Collection의 요소 개수: " + size);
    }
}

```

# 요약
`HashSet`은 고유한 요소들을 저장하기 위한 해시 기반의 자료구조이며, `Collection`은 모든 자바 컬렉션 인터페이스의 최상위 인터페이스로 다양한 데이터 그룹화 및 조작 기능을 제공합니다. 각각의 클래스들은 특정한 요구사항에 맞게 데이터를 저장하고 관리할 수 있도록 설계되어 있습니다.

# 속도 비교
### 중복 값을 확인하고 소요 시간을 측정하는 예제
```Java
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DuplicateCheckExample {

    public static void main(String[] args) {
        // 데이터 초기화
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i % 10000); // 중복 값 생성
        }

        // HashSet을 이용한 중복 값 확인
        long startTimeHashSet = System.currentTimeMillis();
        checkDuplicatesWithHashSet(list);
        long endTimeHashSet = System.currentTimeMillis();
        long elapsedTimeHashSet = endTimeHashSet - startTimeHashSet;
        System.out.println("HashSet을 이용한 중복 값 확인 시간: " + elapsedTimeHashSet + "ms");

        // Collection을 이용한 중복 값 확인
        long startTimeCollection = System.currentTimeMillis();
        checkDuplicatesWithCollection(list);
        long endTimeCollection = System.currentTimeMillis();
        long elapsedTimeCollection = endTimeCollection - startTimeCollection;
        System.out.println("Collection을 이용한 중복 값 확인 시간: " + elapsedTimeCollection + "ms");
    }

    // HashSet을 이용한 중복 값 확인 메서드
    public static void checkDuplicatesWithHashSet(ArrayList<Integer> list) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : list) {
            if (!set.add(num)) {
                // 중복된 값이 HashSet에 이미 존재하면 true 반환
                System.out.println("HashSet에서 중복 발견: " + num);
            }
        }
    }

    // Collection을 이용한 중복 값 확인 메서드
    public static void checkDuplicatesWithCollection(ArrayList<Integer> list) {
        Collection<Integer> collection = new ArrayList<>();
        for (Integer num : list) {
            if (collection.contains(num)) {
                // 중복된 값이 Collection에 이미 존재하면 true 반환
                System.out.println("Collection에서 중복 발견: " + num);
            } else {
                collection.add(num);
            }
        }
    }
}

```

### 실행 결과
```
HashSet을 이용한 중복 값 확인 시간: 34ms 
Collection을 이용한 중복 값 확인 시간: 1127ms
```

# 총평
예상대로 HashSet이 빠르다.


# 번외
### Collection 사용처
`Collection` 인터페이스를 구현한 클래스들 중에서 특정 작업이 더 빠른 경우는 여러 가지가 있을 수 있습니다. 각 클래스는 데이터를 저장하고 관리하는 방식에 따라 성능 차이가 발생할 수 있습니다. 여기에는 몇 가지 예가 있습니다

1. **ArrayList**:

    - 요소의 인덱스를 기반으로 빠른 접근이 가능합니다 (`get()` 메서드).
    - 요소를 순차적으로 추가하거나 접근할 때 성능이 우수합니다.
    - 요소를 중간에 삽입하거나 삭제할 때는 다른 요소들을 이동시켜야 하므로 성능이 저하될 수 있습니다.
2. **LinkedList**:

    - 요소의 삽입과 삭제가 빠르며, 중간에 요소를 추가하거나 삭제할 때도 효율적입니다.
    - 순차적인 접근보다는 임의 접근에 비해 성능이 떨어질 수 있습니다.
3. **HashMap**:

    - 해시 기반의 데이터 구조로 키와 값을 쌍으로 저장합니다.
    - 키를 이용하여 매우 빠르게 값에 접근할 수 있습니다 (`get()` 메서드).
    - 키의 해시 함수가 잘 분산되면 `put()` 및 `get()` 작업이 상수 시간 안에 이루어질 수 있습니다.
4. **TreeSet**:

    - 요소들을 정렬된 순서로 유지하면서 저장합니다.
    - 정렬된 순서를 유지하는 데 필요한 추가적인 작업이 발생할 수 있지만, 정렬된 데이터에 접근이 필요한 경우 빠른 검색이 가능합니다.
5. **ConcurrentHashMap**:

    - 동시에 여러 스레드에서 안전하게 접근할 수 있는 해시 맵입니다.
    - 동기화된 메서드 및 락을 최소화하여 병렬 처리가 가능하며, 효율적인 동시성 제어를 제공합니다.
6. **CopyOnWriteArrayList**:

    - 반복이 많이 발생하고, 요소 변경이 적은 상황에서 유리합니다.
    - 요소를 복사본에 추가하여 수정 작업이 원본에 영향을 주지 않습니다.
    - 반면에 요소를 추가하거나 삭제할 때는 복사본을 만들어야 하므로 성능이 저하될 수 있습니다.

이외에도 각각의 클래스는 자신의 특징과 사용 목적에 맞게 최적화되어 있으며, 데이터의 크기, 구조, 접근 패턴 등에 따라 성능 차이가 발생할 수 있습니다. 따라서 사용하려는 작업에 적합한 자료 구조를 선택하여 성능을 최적화하는 것이 중요합니다.


# 사용처처
HashMap과 Collection은 각각 다양한 사용 사례와 상황에서 유용하게 활용됩니다. 각 자료구조의 특성과 적합한 사용처를 알아보겠습니다.

### HashMap 사용처

1. **데이터 검색 및 접근 속도가 중요한 경우**:

    - HashMap은 해시 기반의 자료구조로, 키와 값을 쌍으로 저장합니다. 이러한 구조는 키를 이용해 매우 빠르게 값을 가져올 수 있습니다 (`O(1)`의 시간 복잡도).
    - 대용량의 데이터에서 특정 키에 대한 검색이 빈번하게 발생하는 경우 유용합니다.
2. **데이터의 순서를 고려하지 않고 빠른 검색이 필요한 경우**:

    - HashMap은 키-값 쌍을 저장할 때 순서를 유지하지 않습니다. 따라서 데이터의 순서가 중요하지 않고, 빠른 검색 속도가 필요한 경우에 적합합니다.
3. **고유한 키를 사용하여 중복을 허용하지 않는 경우**:

    - HashMap은 키가 고유해야 하며, 같은 키에 대해 중복된 값을 저장하지 않습니다. 따라서 고유한 식별자(키)를 사용하여 데이터를 관리해야 할 때 유용합니다.
4. **동시성 처리가 필요하지 않은 단일 스레드 환경**:

    - HashMap은 단일 스레드 환경에서 사용하기에 적합하며, 여러 스레드가 동시에 접근하는 경우에는 ConcurrentHashMap과 같은 동시성 컬렉션을 고려해야 합니다.

### Collection 사용처

1. **순서가 있는 데이터를 저장하고 관리해야 하는 경우**:

    - List 인터페이스를 구현한 ArrayList나 LinkedList는 데이터를 삽입한 순서대로 저장하며, 인덱스를 통해 접근할 수 있습니다. 따라서 데이터의 순서가 중요한 경우에 사용됩니다.
2. **고유한 요소를 저장하고 중복을 허용하지 않아야 하는 경우**:

    - Set 인터페이스를 구현한 HashSet, TreeSet 등은 고유한 요소만을 저장하며 중복된 값을 허용하지 않습니다. 데이터의 유일성이 중요한 경우에 사용됩니다.
3. **동시성 처리가 필요한 멀티 스레드 환경**:

    - 동시성 컬렉션인 ConcurrentHashMap, ConcurrentLinkedQueue 등은 여러 스레드가 동시에 접근할 수 있는 환경에서 안전하게 데이터를 처리할 수 있습니다.
4. **정렬된 데이터를 유지하고 접근해야 하는 경우**:

    - 정렬된 순서를 유지하는 TreeSet은 데이터를 자동으로 정렬하여 저장하며, 정렬된 데이터에 빠르게 접근할 수 있습니다.

### 요약

- **HashMap**: 검색 속도가 중요하고 데이터의 순서가 무의미한 경우에 적합합니다. 고유한 키를 사용하여 중복을 허용하지 않는 데이터를 관리할 때 유용합니다.

- **Collection**: 데이터의 순서가 중요하거나, 고유한 요소를 관리하거나, 동시성 처리가 필요한 경우에 사용됩니다. List, Set, Queue 등의 인터페이스를 구현한 클래스들은 각각의 특성에 맞게 데이터를 저장하고 처리할 수 있습니다.