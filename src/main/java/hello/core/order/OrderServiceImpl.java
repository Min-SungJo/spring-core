package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 이 붙은 것을 토대로 생성자를 만듬
public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    필드 주입(권장하지 않음 -> 외부 변경이 불가능하여 테스트가 힘듬) -> 실제코드와 관계없는 테스트 코드에서는 사용해도 무방(스프링 설정 목적인 @Configuration)
//    @Autowired private DiscountPolicy discountPolicy;
//    @Autowired private MemberRepository memberRepository;
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 설정
//    private  MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 설정
//
//    @Autowired(required = false) // -> 주입할 대상이 없어도 동작
//    public void setMemberRepository(MemberRepository memberRepository) { // 수정자 주입 - > 선택, 변경 가능성이 있는 경우에 사용
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 단계별로 최적화
    // 1. @Autowired 생략
//    @Autowired // -> 생성자가 하나만 있으면 생략 가능
    // 2. @RequiredArgsConstructor -> 전부 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // @Qualifier 를 찾을 수 없으면 -> 명시한 이름을 가진 스프링 빈을 찾는다
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    // 일반 메서드 주입 -> 한번에 여러 필드를 주입 받을 수 있음
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자 주입 -> 불변, 누락 방지, final 키워드 사용 가능(컴파일 오류 확인 가능)
    // 프레임 워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법임(가끔 수정자 주입)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
