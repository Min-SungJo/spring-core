package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String iteName, int itemPrice);
}
