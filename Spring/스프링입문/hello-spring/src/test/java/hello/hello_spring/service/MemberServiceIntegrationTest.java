package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //테스트할 때는 그냥 필드 기반으로 대~충 끌어오는 인젝션으로 된다고 했다.


    //테스트는 과감하게 한글로 바꿔도 된다. 실제 배포하는 코드는 함부로 바꾸면 안되지만 테스트 코드는 직관적으로 보니까 괜찮다.
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /*
        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        이렇게 try-catch문으로 예외처리 잡아도 되지만 애매하다.. 다음과 같은 방법으로 예외처리하자.
         */

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //메세지 검증 방법. 그냥 터트리고 싶으면 밑에처럼 하면 됨.

        //assertThrows(NullPointerException.class, () -> memberService.join(member2)); //실패 사례
        //then
    }

    /*
    이렇게 통합 테스트를 할 수도 있지만 사실 MemberServiceTest처럼 순수한 단위 테스트가 좋은 테스트이다.
    단위를 잘게 쪼개서 테스트를 잘 할 수 있도록 하고 스프링 컨테이너 없이 테스트할 수 있도록 훈련하는 것이 좋다.
    사실 스프링 컨테이너에 올려야되는 이런 경우는 테스트 설계가 잘못되어 있을 확률이 높다.
    통합 테스트도 필요한 경우가 있지만 되도록이면 단위 테스트로 설계하자.
     */

    /*
    잘하는 개발자일수록 테스트 케이스 작성을 잘한다.
    실제 현업에서도 실제 프로덕션 코드와 테스트 코드 짜는 시간을 보면 4:6, 3:7일 정도로 테스트 코드를 작성하는 것일 정도로 중요하다.
    * */

    //아직도 JDBC 템플릿으로 해결안된 것 -> SQL은 개발자가 직접 작성해야함. 이걸 해결하기 위해 JPA가 나왔다,
}