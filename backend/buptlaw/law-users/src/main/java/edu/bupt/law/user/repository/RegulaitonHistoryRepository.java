package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RegulationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulaitonHistoryRepository extends JpaRepository<RegulationHistory, Long> {

    Page<RegulationHistory> findRegulationHistoriesByUser(User user, Pageable pageable);

    Page<RegulationHistory> findRegulationHistoriesByUserAndAction(User user, String action, Pageable pageable);

    RegulationHistory findRegulationHistoryByUserAndRegulationIdAndAction(User user, String regulationId, String action);

    void deleteRegulationHistoryByRegulationIdAndUserAndAction(String regulationId, User user, String action);
}
