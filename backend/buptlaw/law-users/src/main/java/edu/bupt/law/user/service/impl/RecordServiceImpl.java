package edu.bupt.law.user.service.impl;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.repository.RecordRepository;
import edu.bupt.law.user.repository.UserRepository;
import edu.bupt.law.user.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Record> getRecordsByPage(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return recordRepository.getRecordsByUser(user, pageable);
    }

    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public Record addRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record updateRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Boolean deleteRecord(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return false;
        }
        record.setStatus(2);
        recordRepository.save(record);
        return true;
    }
}
