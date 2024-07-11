package zerock.boot02;

import zerock.boot02.domain.Board;
import zerock.boot02.dto.BoardListReplyCountDTO;
import zerock.boot02.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

//    @Test
//    public void testInsert() {
//        for (int i = 0; i < 100; i++) {
//            Board board = Board.builder()
//                    .title("title" + i)
//                    .content("This is Content in title(" + i + ")")
//                    .writer("user" + (i % 10))
//                    .build();
//            Board result = boardRepository.save(board);
//            log.info("BNO : " + result.getBno());
//        }
//    }

    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info("---------------------------------");
        log.info("board : " + board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update.. title update", "update content update");
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count : {}", result.getTotalElements());
        log.info("total pages : {}", result.getTotalPages());
        log.info("page number : {}", result.getNumber());
        log.info("page size : {}", result.getSize());

        List<Board> boards = result.getContent();
        for (Board board : boards) {
            log.info(board.toString());
        }
    }

    @Test
    public void testSearch1() {

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        //검색어로 검색조건(제목,내용,글쓴이)에 맞게 페이지 검색
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //결과 확인
        log.info(result.getTotalPages());//전체 페이지
        log.info(result.getNumber());  //페이지번호
        log.info(result.hasPrevious() + ": " + result.hasNext());
        result.getContent().forEach(board -> log.info(board.toString()));
    }

    @Test
    public void testSearchReplyCount() {

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.
                searchWithReplyCount(types, keyword, pageable);

        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }
}
