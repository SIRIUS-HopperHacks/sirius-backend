package com.app.sirius.repository;

import com.app.sirius.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findByAlertId(Long alertId);
}
