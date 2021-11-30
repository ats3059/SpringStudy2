package hello.servlet.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Member2 {

    private Long id;
    private String username;
    private int age;
//
//    // 빌더 소환: 외부에서 Car.builder() 형태로 접근 가능하게 스태틱 메소드로
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    private Member2(Builder builder){
//        this.id = builder.id;
//        this.username = builder.username;
//        this.age = builder.age;
//    }
//
//
//    public static class Builder{
//        private Long id;
//        private String username;
//        private int age;
//
//        public Builder setId(Long value){
//            this.id = value;
//            return this;
//        }
//
//        public Builder setUserName(String value){
//            this.username = value;
//            return this;
//        }
//
//        public Builder setAge(int age){
//            this.age = age;
//            return this;
//        }
//
//        public Member2 build(){
//            return new Member2(this);
//        }
//
//    }




}
