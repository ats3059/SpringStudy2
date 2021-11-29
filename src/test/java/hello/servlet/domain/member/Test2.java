package hello.servlet.domain.member;

import org.junit.jupiter.api.Test;

public class Test2 {

    @Test
    void builderTest(){
        Member2 member2 = Member2.builder()
                                .setAge(10)
                                .setId(10L)
                                .setUserName("25").build();

        System.out.println("member2.getUsername() = " + member2.getUsername());


    }
}
