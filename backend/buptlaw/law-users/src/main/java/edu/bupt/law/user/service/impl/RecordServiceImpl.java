package edu.bupt.law.user.service.impl;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Override
    public Page<Record> getRecordsByPage(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public Record getRecordById(Long id) {
        return null;
    }

    @Override
    public void addRecord(Record record) {

    }

    @Override
    public void updateRecord(Record record) {

    }

    @Override
    public void deleteRecord(Long id) {

    }
}
