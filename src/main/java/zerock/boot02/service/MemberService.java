package zerock.boot02.service;

import zerock.boot02.dto.MemberJoinDTO;

public interface MemberService {

    void modify(MemberJoinDTO memberJoinDTO) throws MidExistException;

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}
