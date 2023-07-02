package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션에 대한 환경설정을 담당하는 클래스
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결)
 *
 * 역할과 구현 클래스가 한 눈에 들어오도록 만듬,
 * 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능
 * */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
//    public static MemberRepository memberRepository() { -> static method 는 싱글톤을 보장받지 못함
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    return null; // -> 필드 주입 예시를 위한 임시 조치
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 클라이언트 코드를 변경하지 않고도 기능을 확장할 수 있음
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
