package hello.servlet.domain.member;

import hello.servlet.ServletApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Test2 {

    @Test
    void builderTest(){


        Member2 member2 = Member2.builder().age(10).id(12L).username("123").build();
        Member2 member21 = new Member2(12L,"123",123);


        ApplicationContext ac = new AnnotationConfigApplicationContext(ServletApplication.class);

        Arrays.stream(ac.getBeanDefinitionNames()).forEach((value) -> System.out.println("value = " + value));




        System.out.println("member2.getUsername() = " + member2.getUsername());


    }
}
