package com.app.hopperhacks.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name="item_code")
    private Long item_code;

    @Column(name="item_name", nullable = false)
    private String item_name;

    @Column(name = "item_description", nullable = true, length=500)
    private String item_description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="item_regTime", columnDefinition = "datetime default now()", updatable = false)
    private LocalDateTime item_regTime;

    @Column(name="item_info", length=500)
    private String item_info;


}
