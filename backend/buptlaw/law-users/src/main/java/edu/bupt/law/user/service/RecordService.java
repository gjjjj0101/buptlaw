package edu.bupt.law.user.service;

import edu.bupt.law.user.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {
    Page<Record> getRecordsByPage(Long userId, Pageable pageable);

    Record getRecordById(Long id);

    void addRecord(Record record);

    void updateRecord(Record record);

    void deleteRecord(Long id);


}
