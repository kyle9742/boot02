package zerock.boot02.service;

import zerock.boot02.dto.PageRequestDTO;
import zerock.boot02.dto.PageResponseDTO;
import zerock.boot02.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO PageRequestDTO);
}
