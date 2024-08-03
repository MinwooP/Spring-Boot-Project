package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id에 해당하는 멤버를 등록
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    // id 에 해당하는 member를 탐색 및 반환
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    // 모든 member를 반환
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    // 특정 name에 해당하는 member를 반환
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 member를 삭제
    public void clearStore() {
        store.clear();
    }
}
