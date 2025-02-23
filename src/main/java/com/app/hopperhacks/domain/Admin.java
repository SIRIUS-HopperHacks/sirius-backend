package com.app.hopperhacks.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tb_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AdminId", unique = true, nullable = false)
    private Long adminId; //code값

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password;

    @Transient
    @ToString.Exclude
    @JsonIgnore
    private String repassword;

    @Column(name="organizationType", nullable=false)
    private String organizationType;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdTime", nullable=false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdTime;

}
