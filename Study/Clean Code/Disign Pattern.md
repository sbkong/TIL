디자인 패턴은 소프트웨어 설계에서 자주 발생하는 문제를 해결하기 위한 재사용 가능한 솔루션을 제시하는 것입니다. 다양한 디자인 패턴이 있으며, 그중 **데코레이터 패턴**과 **옵저버 패턴**은 매우 자주 사용되는 패턴입니다. 각 패턴을 자세히 설명해드리겠습니다.

### 1. 데코레이터 패턴 (Decorator Pattern)
**데코레이터 패턴**은 객체에 추가적인 기능을 동적으로 부여할 수 있는 패턴입니다. 이 패턴은 상속을 사용하지 않고도 객체의 행동을 확장할 수 있도록 합니다.

- **핵심 개념**:
    - 데코레이터 패턴은 기존 객체의 메서드나 속성을 수정하지 않고, 그 객체에 추가적인 기능을 덧붙이기 위해 사용됩니다.
    - 데코레이터는 동일한 인터페이스를 구현하여 원래 객체를 감싸고, 원래 객체에 추가적인 행동을 수행할 수 있게 합니다.
    - 이러한 구조를 통해 객체의 기능을 점진적으로 확장하거나 변경할 수 있습니다.

- **예시**:
    - 예를 들어, 커피를 만드는 시스템에서 기본 커피에 "우유 추가", "시럽 추가" 등의 기능을 동적으로 추가할 수 있다고 생각해 보세요. 커피 객체를 기본으로 하고, 우유 데코레이터, 시럽 데코레이터 등을 사용하여 다양한 종류의 커피를 만들 수 있습니다.

```python
class Coffee:
    def cost(self):
        return 5

class MilkDecorator:
    def __init__(self, coffee):
        self.coffee = coffee

    def cost(self):
        return self.coffee.cost() + 1  # 우유 추가 비용

basic_coffee = Coffee()
coffee_with_milk = MilkDecorator(basic_coffee)
print(coffee_with_milk.cost())  # 6
```

### 2. 옵저버 패턴 (Observer Pattern)
**옵저버 패턴**은 객체 상태의 변화에 따라 다른 객체들이 자동으로 통보받고 업데이트될 수 있도록 하는 패턴입니다. 이 패턴은 주로 이벤트 처리 시스템이나 상태 변화에 민감한 시스템에서 사용됩니다.

- **핵심 개념**:
    - 옵저버 패턴에서는 **주체(Subject)**와 **옵저버(Observer)**라는 두 가지 주요 객체가 존재합니다.
    - 주체 객체는 상태를 가지고 있으며, 이 상태가 변경되면 옵저버들에게 그 변경을 통보합니다.
    - 옵저버들은 주체 객체에 등록되어 있으며, 주체의 상태 변화에 대해 관심을 가지고 있습니다.
    - 주체가 상태를 변경하면, 옵저버들이 이를 감지하고 적절히 반응합니다.

- **예시**:
    - 예를 들어, 뉴스 애플리케이션에서 새로운 기사가 등록될 때, 애플리케이션에 연결된 모든 사용자가 새 기사를 통보받는 시나리오를 생각해 볼 수 있습니다.

```python
class Subject:
    def __init__(self):
        self.observers = []
        self.state = None

    def attach(self, observer):
        self.observers.append(observer)

    def detach(self, observer):
        self.observers.remove(observer)

    def notify(self):
        for observer in self.observers:
            observer.update(self.state)

    def set_state(self, state):
        self.state = state
        self.notify()

class Observer:
    def update(self, state):
        print(f"Observer received state change: {state}")

subject = Subject()
observer1 = Observer()
observer2 = Observer()

subject.attach(observer1)
subject.attach(observer2)

subject.set_state("New State")  # 모든 옵저버에게 통보
```

이 두 가지 패턴은 객체 지향 설계에서 매우 유용하며, 재사용성과 유지보수성을 높이는 데 도움을 줍니다.