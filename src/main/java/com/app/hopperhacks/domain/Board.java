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
    @Column(name="board_code")
    private Long board_code;


    @Column(name="board_title", nullable = false, length = 300)
    private String board_title;

    @Column(name="board_title", nullable=true)
    private String board_content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="board_time", columnDefinition = "datetime default now()", updatable = false)
    //@Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime board_time;

    @Column(name="board_like", nullable = true, columnDefinition = "int default 0")
    private int board_like; //TODO 한계정당 한번만 누를수 있게 할건지, 아님 무한클릭 되게할건지
    @Column(name="board_dislike", nullable = true, columnDefinition = "int default 0")
    private int board_dislike; //TODO
    // 한계정당 한번만 누르게 하려면 어느 USER가 어느 BOARD에 좋아요를 했는지를 따로 테이블 파서 기록해야됨
    /*
    LIKE_DISLIKE_TABLE 예시
    USER 정보
    BOARD 정보
    LIKE 유무
    DISLIKE 유무
     */


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_code", referencedColumnName = "user_code")
    private User user;
}
