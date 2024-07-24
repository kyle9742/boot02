package zerock.boot02.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import zerock.boot02.domain.Member;
import zerock.boot02.domain.MemberRole;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .mid("test" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@aaa.com")
                    .build();

            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {

        Optional<Member> result = memberRepository.getWithRoles("test100");

        Member member = result.orElse(null);

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }

    @Test
    public void testUpdate() {

        String mid = "jaewoo1993@gmail.com";
        String mpw = passwordEncoder.encode("1111");

        memberRepository.updatePassword(mpw, mid);
    }
}
