package edu.bupt.law.user.repository;

import edu.bupt.law.user.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
