package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientPrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
//        assertThat(count2).isEqualTo(2); // 이미 주입된 Bean
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
//    @RequiredArgsConstructor
    static class ClientBean {

        //ObjectProvider
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider; // ObjectFactory 상속, 추가적으로 기능을 제공
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        // 생성자로 해도 되지만 필드주입이 간단하니, 이 예시에서는 필드주입 사용

//        private final PrototypeBean prototypeBean; // 생성시점에 주입
//        @Autowired
//        ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        //JSR-330 Provider, 자바 표준(ObjectProvider 는 스프링에 의존적임), 단순함, 라이브러리 추가해야함(ObjectProvider 는 추가 필요 x)
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

//        @Autowired
//        ApplicationContext applicationContext; // 비효율적
//
//        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
    }
        // 여러 빈에서 같은 PrototypeBean 을 주입 받으면, 각 시점마다 새로운 PrototypeBean 이 생성됨 (x01, x02)
        // 사용할 때마다 생성되는 것은 아님
//    @Scope("singleton")
////    @RequiredArgsConstructor
//    static class ClientBean2 {
//        private final PrototypeBean prototypeBean;
//
//        @Autowired
//        ClientBean2(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
//    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }


}
