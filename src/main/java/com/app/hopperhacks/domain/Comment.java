package com.app.hopperhacks.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_code")
    private Long comment_code;

    @Column(name="comment_content", nullable=false)
    private String comment_content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="comment_time", columnDefinition = "datetime default now()", updatable = false)
    private LocalDateTime comment_time;

    @Column(name="comment_like", nullable=true, columnDefinition = "int default 0")
    private int comment_like;

    @Column(name="comment_dislike", nullable=true, columnDefinition = "int default 0")
    private int comment_dislike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_code", referencedColumnName = "user_code")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_code", referencedColumnName = "board_code")
    private Board board;
}
