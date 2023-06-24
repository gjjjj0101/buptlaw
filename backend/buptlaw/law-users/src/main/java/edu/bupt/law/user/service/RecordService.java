package edu.bupt.law.user.service;

import edu.bupt.law.user.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {
    Page<Record> getRecordsByPage(String username, Pageable pageable);

    Record getRecordById(Long id);

    Record addRecord(String title, String description, String content, String type, String username);

    Record updateRecord(Long id, String title, String description, String content, String type, String username);

    Boolean deleteRecord(Long id);


}
