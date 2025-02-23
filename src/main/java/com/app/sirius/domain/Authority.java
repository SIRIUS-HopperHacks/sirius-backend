package com.app.sirius.domain;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tb_authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="authCode")
    private Long authCode;

    @Column(name="authName", length=40, unique=true)
    private String authName; //("MEMBER", "ADMIN")
}
