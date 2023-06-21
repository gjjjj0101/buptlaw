package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.history.RegulationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulaitonHistoryRepository extends JpaRepository<RegulationHistory, Long> {
}
