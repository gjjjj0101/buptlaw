package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RecordHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordHistoryRepository extends JpaRepository<RecordHistory, Long> {
    Page<RecordHistory> findRecordHistoriesByUser(User user, Pageable pageable);

    Page<RecordHistory> findRecordHistoriesByUserAndType(User user, String type, Pageable pageable);

    Page<RecordHistory> findRecordHistoriesByUserAndAction(User user, String action, Pageable pageable);
}
