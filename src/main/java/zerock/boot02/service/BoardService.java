package zerock.boot02.service;

import zerock.boot02.domain.Board;
import zerock.boot02.dto.*;
import zerock.boot02.dto.BoardDTO;
import zerock.boot02.dto.PageRequestDTO;
import zerock.boot02.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void delete(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
