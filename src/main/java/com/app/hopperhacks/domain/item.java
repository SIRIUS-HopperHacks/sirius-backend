package com.app.hopperhacks.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tb_item")
public class item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemCode")
    private Long itemCode;

    @Column(name="itemName", nullable = false)
    private String itemName;

    @Column(name = "itemDescription", nullable = true, length=500)
    private String itemDescription;

    @Column(name="itemRegTime", columnDefinition = "timestamp default current_timestamp", updatable = false)
    private LocalDateTime itemRegTime;

    @Column(name="itemInfo", length=500)
    private String itemInfo;


}
