package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        // 탐색할 패키지의 시작 위치 지정
//        basePackageClasses = AutoAppConfig.class, -> 지정한 클래스의 패키지를 탐색 시작 위로 지정, 지정하지 않으면 상단의 package hello.core; 부터!
        // 권장 - default 에 따라 AppConfig 의 패키지 위치를 지정하지 않고 현재 위치(프로젝트 최상단)에 두는 것
        // @SpringBootApplication 에 위 설정이 포함되어 있음

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 제외할 것 명시, AppConfig 와 중복되기 때문에 위 코드를 사용해서 제외(기존 예제코드를 남기기 위해)

)
public class AutoAppConfig {

    // 중복 bean -> 수동 등록된 것이 우선권을 가짐
    // Overriding bean definition for bean 'memoryMemberRepository' with a different definition
    // application.properties 에 spring.main.allow-bean-definition-overriding=true 기입하면 중복이 허용됨
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
