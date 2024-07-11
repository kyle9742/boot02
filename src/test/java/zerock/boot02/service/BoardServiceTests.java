package zerock.boot02.service;

import zerock.boot02.domain.Board;
import zerock.boot02.dto.BoardDTO;
import zerock.boot02.dto.PageRequestDTO;
import zerock.boot02.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerock.boot02.dto.BoardDTO;
import zerock.boot02.dto.PageRequestDTO;
import zerock.boot02.dto.PageResponseDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("샘플 타이틀")
                .content("샘플 내용")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testModify() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(701L)
                .title("수정 타이틀")
                .content("수정 내용")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testDelete() {
        log.info(boardService.getClass().getName());

        boardService.delete(701L);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }
}
