package edu.bupt.law.user.service.impl;

import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.model.history.RegulationHistory;
import edu.bupt.law.user.repository.RecordHistoryRepository;
import edu.bupt.law.user.repository.RegulaitonHistoryRepository;
import edu.bupt.law.user.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToOne;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private RecordHistoryRepository recordHistoryRepository;
    @Autowired
    private RegulaitonHistoryRepository regulaitonHistoryRepository;

    @Override
    public Page<RecordHistory> getRecordsHistoryByPage(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RegulationHistory> getRegulationsHistoryByPage(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RegulationHistory> getRegulationsHistoryByPageAndAction(Long userId, String action, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RecordHistory> getRecordsHistoryByPageAndAction(Long userId, String action, Pageable pageable) {
        return null;
    }

    @Override
    public void addRecordHistory(Long userId, Long recordId, String action) {

    }

    @Override
    public void addRegulationHistory(Long userId, String regulationId, String title, String action) {

    }
}
