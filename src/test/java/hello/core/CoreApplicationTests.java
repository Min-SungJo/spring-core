package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

//	@Autowired // -> 실제 코드와 관계가 없기 때문에 필드 주입을 사용해도 무방하다
//	OrderService orderService;
	@Test
	void contextLoads() {
	}

}
