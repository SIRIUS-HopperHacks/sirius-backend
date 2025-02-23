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
    @Column(name="alert_id", unique=true, nullable=false)
    private Long alertId;

    @Column(name="device_id",nullable=false)
    private String deviceId;

    @Column(name="device_location", nullable=false)
    private String deviceLocation;

    @Column(name="alert_type")
    private String alertType;

    @Column(name="alerted_time", nullable=false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime alertedTime;



}
