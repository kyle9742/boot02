package zerock.boot02;

import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import zerock.boot02.domain.Board;
import zerock.boot02.domain.BoardImage;
import zerock.boot02.dto.BoardListAllDTO;
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
import zerock.boot02.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

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

    @Test
    public void testInsertWithImages() {

        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {

            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages() {

        Optional<Board> result = boardRepository.findByIDWithImages(2L);

        Board board = result.orElseThrow();

        log.info(board);
        log.info("--------------------------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }

    @Test
    @Commit
    @Transactional
    public void testModifyImages() {

        Optional<Board> result = boardRepository.findByIDWithImages(2L);

        Board board = result.orElseThrow();

        board.clearImages();

        for (int i = 0; i < 2; i++) {

            board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {

        Long bno = 2L;

        replyRepository.deleteByBoard_Bno(bno);

        boardRepository.deleteById(bno);
    }

    @Test
    public void testInsertAll() {

        for (int i = 1; i <= 100; i++) {

            Board board = Board.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .writer("Writer.." + i)
                    .build();

            for (int j = 0; j < 3; j++) {

                if (i % 5 == 0) {
                    continue;
                }
                board.addImage(UUID.randomUUID().toString(), i + "file" + j + ".jpg");
            }
            boardRepository.save(board);
        }
    }

    @Transactional
    @Test
    public void testSearchImageReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        boardRepository.searchWithAll(null, null, pageable);

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);

        log.info("----------------------------------");
        log.info(result.getTotalElements());

        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
    }
}
