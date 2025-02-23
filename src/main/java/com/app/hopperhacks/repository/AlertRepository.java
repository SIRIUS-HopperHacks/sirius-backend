package com.app.hopperhacks.repository;

import com.app.hopperhacks.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findByAlertId(Long alertId);
}
