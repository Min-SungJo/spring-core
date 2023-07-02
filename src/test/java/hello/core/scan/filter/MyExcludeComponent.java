package hello.core.scan.filter;

import java.lang.annotation.*;

/**
 * filter 에서 제외할 컴포넌트
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
