package zerock.boot02.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

	@CreatedDate
	@Column(name="regdate", updatable = false)
	private LocalDateTime regDate;	//등록날짜 (데이터 생성시에만 생성O / 수정시에는 생성X )

	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;	//수정날짜
}
