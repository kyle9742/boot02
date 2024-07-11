package zerock.boot02.repository;

import zerock.boot02.domain.Board;
import zerock.boot02.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository 상속 후 <TableClassName, IDtype>
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
// 	CRUD 자동 상속
// 	Custom Method
//	@Query("select b from Board b where b.title like concat('%',:keyword,'%')")
//	Page<Board> findKeyword(String keyword, Pageable pageable);
}
