package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 제외할 것 명시, AppConfig 와 중복되기 때문에 위 코드를 사용해서 제외(기존 예제코드를 남기기 위해)
)
public class AutoAppConfig {

}
