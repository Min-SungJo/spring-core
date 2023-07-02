package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 호출될 때마다 반환값이 변경됨
    // 공유되는 값을 변경할 수 있다는 문제점
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
