package hello.core.singleton;

public class SingletonService {

    // 객체를 미리 생성해두는 방법 - 단순, 안전
    // 클래스가 구체 클래스에 의존하여 DIP 위반, 유연성이 떨어짐, 테스트가 어려움

    public static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() { // 이 메서드를 통해서만 객체 인스턴스를 조회할 수 있음
        return instance;
    }

    private SingletonService() { // 생성자를 private 으로 막아서, 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
