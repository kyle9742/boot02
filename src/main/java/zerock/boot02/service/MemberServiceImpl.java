package zerock.boot02.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerock.boot02.domain.Board;
import zerock.boot02.domain.Member;
import zerock.boot02.domain.MemberRole;
import zerock.boot02.dto.MemberJoinDTO;
import zerock.boot02.repository.MemberRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void modify(MemberJoinDTO memberJoinDTO) throws MidExistException {

        Optional<Member> result = memberRepository.findById(memberJoinDTO.getMid());
        Member member = result.orElseThrow();
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        memberRepository.save(member);
    }

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {

        String mid = memberJoinDTO.getMid();

        boolean exist = memberRepository.existsById(mid);

        if (exist) {
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("====================================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
    }

}
