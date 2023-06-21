package edu.bupt.law.user.service;

import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.model.history.RegulationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

public interface HistoryService {

    Page<RecordHistory> getRecordsHistoryByPage(Long userId, Pageable pageable);

    Page<RegulationHistory> getRegulationsHistoryByPage(Long userId, Pageable pageable);

    Page<RegulationHistory> getRegulationsHistoryByPageAndAction(Long userId, String action, Pageable pageable);

    Page<RecordHistory> getRecordsHistoryByPageAndAction(Long userId, String action, Pageable pageable);

    void addRecordHistory(Long userId, Long recordId, String action);

    void addRegulationHistory(Long userId, String regulationId, String title, String action);
}
