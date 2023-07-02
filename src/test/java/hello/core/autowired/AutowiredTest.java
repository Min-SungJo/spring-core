package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    static class TestBean {

        @Autowired(required = false) // 자동 주입 대상이 없으면 호출 자체가 안 됨
        public void setNoBean1(Member noBean1) { // Member 는 스프링 컨테이너와 관련이 없음(스프링 빈이 아님)
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // 호출은 되나, null
            System.out.println("noBean2 = " + noBean2);
        }
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 호출은 되나, Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
