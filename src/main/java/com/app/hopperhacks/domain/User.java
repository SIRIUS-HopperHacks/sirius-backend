package com.app.hopperhacks.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_code")
    private Long user_code;

    @Column(name="user_name", nullable = false)
    private String user_name;

    @Column(name="user_id", unique = true, nullable = false)
    private String user_id;

    @Column(name="user_password", nullable = false)
    @JsonIgnore
    private String user_password;

    @Transient
    @ToString.Exclude
    @JsonIgnore
    private String user_repassword;

    @Column(name="user_age", nullable=true)
    private int user_age;

    @Column(name="user_phone", nullable=true, length=11)
    private int user_phone;

    @Column(name="user_gender", nullable=true)
    private String user_gender;

    @Column(name="user_addr1", nullable=true)
    private String user_addr1;

    @Column(name="user_addr2", nullable=true)
    private String user_addr2;

    @Column(name="user_addr3", nullable=true)
    private String user_addr3;

    @Column(name="user_zipcode", nullable=true)
    private int user_zipcode;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority... authorities){
        Collections.addAll(this.authorities, authorities);
    }

}
