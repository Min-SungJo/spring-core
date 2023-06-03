package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 애플리케이션에 대한 환경설정을 담당하는 클래스
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결)
 *
 * 역할과 구현 클래스가 한 눈에 들어오도록 만듬,
 * 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능
 * */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
