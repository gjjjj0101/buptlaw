package edu.bupt.law.user.service.impl;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.repository.RecordRepository;
import edu.bupt.law.user.repository.UserRepository;
import edu.bupt.law.user.service.HistoryService;
import edu.bupt.law.user.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoryService historyService;

    @Override
    public Page<Record> getRecordsByPage(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return recordRepository.getRecordsByUserAndStatus(user, 1, pageable);
    }

    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public Record addRecord(String title, String description, String content, String type, String username) {
        Record record = new Record();
        User user = userRepository.findByUsername(username);
        record.setContent(content).setStatus(1).setTitle(title).setType(type)
                .setDescription(description).setUser(user).setCreateDateTime(LocalDateTime.now()).setUpdateDateTime(LocalDateTime.now());
        Record saveRecord = recordRepository.save(record);
        historyService.addRecordHistory(username, saveRecord.getId(), "01");
        return saveRecord;
    }

    @Override
    public Record updateRecord(Long id, String title, String description, String content, String type, String username) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return null;
        }
        if (!record.getUser().getUsername().equals(username)){
            return null;
        }
        record.setTitle(title).setDescription(description).setContent(content).setType(type).setUpdateDateTime(LocalDateTime.now());
        historyService.addRecordHistory(username, record.getId(), "02");
        return recordRepository.save(record);
    }

    @Override
    public Boolean deleteRecord(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return false;
        }
        record.setStatus(2).setDeleteDateTime(LocalDateTime.now());
        recordRepository.save(record);
        historyService.addRecordHistory(record.getUser().getUsername(), record.getId(), "03");
        return true;
    }
}
