package zerock.boot02.repository.search;

import zerock.boot02.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zerock.boot02.dto.BoardListAllDTO;
import zerock.boot02.dto.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

}
