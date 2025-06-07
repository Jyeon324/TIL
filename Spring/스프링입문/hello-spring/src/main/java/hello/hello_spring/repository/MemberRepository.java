package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //Optional은 null을 반환할 경우 null로 반환하는 것보다 Optional이라는 것으로 감싸서 반환하는 것임
    List<Member> findAll();
}
