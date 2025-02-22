package com.app.hopperhacks.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@DynamicInsert //insert시 null field 제외
@DynamicUpdate //update시 위와 동일
@Entity
@Table(name="tb_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="boardCode")
    private Long boardCode;


    @Column(name="boardTitle", nullable = false, length = 300)
    private String boardTitle;

    @Column(name="boardContent", nullable=true)
    private String boardContent;

    @Column(name="boardTime", columnDefinition = "timestamp default current_timestamp", updatable = false)
    //@Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime boardTime;

    @Column(name="boardLike", nullable = true, columnDefinition = "int default 0")
    private int boardLike; //TODO 한계정당 한번만 누를수 있게 할건지, 아님 무한클릭 되게할건지
    @Column(name="boardDislike", nullable = true, columnDefinition = "int default 0")
    private int boardDislike; //TODO
    // 한계정당 한번만 누르게 하려면 어느 USER가 어느 BOARD에 좋아요를 했는지를 따로 테이블 파서 기록해야됨
    /*
    LIKE_DISLIKE_TABLE 예시
    USER 정보
    BOARD 정보
    LIKE 유무
    DISLIKE 유무
     */


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userCode", referencedColumnName = "userCode")
    private User user;
}
