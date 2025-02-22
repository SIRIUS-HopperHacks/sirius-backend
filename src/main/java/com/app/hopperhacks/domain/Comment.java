package com.app.hopperhacks.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(name="commentCode")
    private Long commentCode;

    @Column(name="commentContent", nullable=false)
    private String commentContent;

    @Column(name="commentTime", columnDefinition = "timestamp default current_timestamp", updatable = false)
    private LocalDateTime commentTime;

    @Column(name="commentLike", nullable=true, columnDefinition = "integer default 0")
    private int commentLike;

    @Column(name="commentDislike", nullable=true, columnDefinition = "integer default 0")
    private int commentDislike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userCode", referencedColumnName = "userCode")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="boardCode", referencedColumnName = "boardCode")
    private Board board;
}
