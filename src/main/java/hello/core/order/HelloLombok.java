package hello.core.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asf");

        System.out.println("helloLombok = " + helloLombok);
        // helloLombok = HelloLombok(name=asf, age=0)
    }
}
