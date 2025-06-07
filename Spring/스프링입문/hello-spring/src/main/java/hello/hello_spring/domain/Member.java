package hello.hello_spring.domain;

/*
JPA는 객체랑 ORM(Object Relational Mapping)이라는 기술이다. 객체의 오브젝트와 관계형 데이터베이스의 테이블을 맵핑하는 기술.
* */

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
