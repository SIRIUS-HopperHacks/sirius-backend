package com.app.hopperhacks.domain;


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
    @Column(name="auth_code")
    private Long auth_code;

    @Column(name="auth_name", length=40, nullable=false, unique=true)
    private String auth_name; //("MEMBER", "ADMIN")
}
