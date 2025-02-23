package com.app.sirius.domain;


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
    @Column(name="admin_id", unique = true, nullable = false)
    private Long adminId; //code값

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name="organization_type", nullable=false)
    private String organizationType;

    @Column(name="created_time", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdTime;

}
