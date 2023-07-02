package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
        // 싱글톤 패턴을 통해 해당 객체를 하나만 생성하여 공유하여 사용할 수 있음
    }

//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService();
//    } -> private 이라 외부에서 호출이 불가능

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2); // 같은 인스턴스 객체를 반환
        assertThat(singletonService1).isSameAs(singletonService2); // 인스턴스 비교이기에 isSameAs
        // same 은 ==
        // equal 는 .equals
    }
    // 스프링 컨테이너는 객체를 싱글톤 패턴으로 사용하기 때문에, 이전에 만든 Singleton 객체를 AppConfig 에 적용할 필요가 없음
}
