package edu.bupt.law.user.service.impl;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.model.history.RegulationHistory;
import edu.bupt.law.user.repository.RecordHistoryRepository;
import edu.bupt.law.user.repository.RecordRepository;
import edu.bupt.law.user.repository.RegulaitonHistoryRepository;
import edu.bupt.law.user.repository.UserRepository;
import edu.bupt.law.user.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private RecordHistoryRepository recordHistoryRepository;
    @Autowired
    private RegulaitonHistoryRepository regulaitonHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Page<RecordHistory> getRecordsHistoryByPage(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return recordHistoryRepository.findRecordHistoriesByUser(user, pageable);
    }

    @Override
    public Page<RegulationHistory> getRegulationsHistoryByPage(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return regulaitonHistoryRepository.findRegulationHistoriesByUser(user, pageable);
    }

    @Override
    public Page<RegulationHistory> getRegulationsHistoryByPageAndAction(String username, String action, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return regulaitonHistoryRepository.findRegulationHistoriesByUserAndAction(user, action, pageable);
    }

    @Override
    public Page<RecordHistory> getRecordsHistoryByPageAndAction(String username, String action, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return recordHistoryRepository.findRecordHistoriesByUserAndAction(user, action, pageable);
    }

    @Override
    public RecordHistory addRecordHistory(String username, Long recordId, String action) {
        User user = userRepository.findByUsername(username);
        Record record = recordRepository.findById(recordId).orElse(null);
        if (record == null) {
            return null;
        }
        RecordHistory recordHistory = new RecordHistory();
        recordHistory.setUser(user).setRecord(record).setAction(action).setCreateTime(LocalDateTime.now());
        return recordHistoryRepository.save(recordHistory);
    }

    @Override
    public RegulationHistory addRegulationHistory(String username, String regulationId, String title, String action) {
        User user = userRepository.findByUsername(username);
        RegulationHistory regulationHistory = new RegulationHistory();
        regulationHistory.setAction(action).setCreateTime(LocalDateTime.now()).setUser(user).setRegulationId(regulationId).setTitle(title);
        return regulaitonHistoryRepository.save(regulationHistory);
    }
}
