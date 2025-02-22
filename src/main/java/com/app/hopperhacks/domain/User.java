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
    @Column(name="userCode")
    private Long userCode;

    @Column(name="userName", nullable = false)
    private String userName;

    @Column(name="userId", unique = true, nullable = false)
    private String userId;

    @Column(name="userPassword", nullable = false)
    @JsonIgnore
    private String userPassword;

    @Transient
    @ToString.Exclude
    @JsonIgnore
    private String userRepassword;

    @Column(name="userAge", nullable=true)
    private int userAge;

    @Column(name="userPhone", nullable=true, length=11)
    private int userPhone;

    @Column(name="userGender", nullable=true)
    private String userGender;

    @Column(name="userAddr1", nullable=true)
    private String userAddr1;

    @Column(name="userAddr2", nullable=true)
    private String userAddr2;

    @Column(name="userAddr3", nullable=true)
    private String userAddr3;

    @Column(name="userZipcode", nullable=true)
    private int userZipcode;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority... authorities){
        Collections.addAll(this.authorities, authorities);
    }

}
