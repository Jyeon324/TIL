package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    //Map이 뭔지 HashMap이 뭔지 공부해야함.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //stream이 뭔지 findAny가 뭔지 공부해야 함.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //실무에서 자바에서 List가 편리해서 많이 쓴다.
    }

    public void clearStore() {
        store.clear();
    }
}

// 이거 구현하고 어떻게 검증하지? -> 기가막힘. 테스트 케이스를 작성하면 검증 가능 ㄷㄷ