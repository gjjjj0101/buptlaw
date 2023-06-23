package edu.bupt.law.user.service;

import edu.bupt.law.user.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {
    Page<Record> getRecordsByPage(String username, Pageable pageable);

    Record getRecordById(Long id);

    Record addRecord(Record record);

    Record updateRecord(Record record);

    Boolean deleteRecord(Long id);


}
