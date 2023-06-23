package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> getRecordsByUser(User user, Pageable pageable);

}
