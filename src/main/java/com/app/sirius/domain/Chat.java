package com.app.sirius.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tb_chat")
public class Chat {

    @Id
    @Column(name="chat_id", updatable = false, nullable = false)
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connection_id")
    @ToString.Exclude  // 순환 참조 방지
    private Chatroom chatroom;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime sentAt;
}
