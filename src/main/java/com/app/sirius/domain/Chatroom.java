package com.app.sirius.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tb_chatroom")
public class Chatroom {
    @Id
    @Column(name="connection_id", updatable = false, nullable = false)
    private Long connectionId;

    @Column(name="device_id1", nullable = false)
    private String deviceId1;

    @Column(name="device_id2", nullable = false)
    private String deviceId2;

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();
}
