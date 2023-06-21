package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.history.RecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordHistoryRepository extends JpaRepository<RecordHistory, Long> {
}
