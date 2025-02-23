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
@Table(name="tb_alert")
public class Alert {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="alertId", unique=true, nullable=false)
    private Long alertId;

    @Column(name="deviceId",nullable=false)
    private String deviceId;

    @Column(name="deviceLocation", nullable=false)
    private String deviceLocation;

    @Column(name="alertType")
    private String alertType;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name="alertedTime", nullable=false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime alertedTime;



}
