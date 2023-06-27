package edu.bupt.law.user.service;

import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.model.history.RegulationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

public interface HistoryService {

    Page<RecordHistory> getRecordsHistoryByPage(String username, Pageable pageable);

    Page<RegulationHistory> getRegulationsHistoryByPage(String username, Pageable pageable);

    Page<RegulationHistory> getRegulationsHistoryByPageAndAction(String username, String action, Pageable pageable);

    Page<RecordHistory> getRecordsHistoryByPageAndAction(String username, String action, Pageable pageable);

    RecordHistory addRecordHistory(String username, Long recordId, String action);

    RegulationHistory addRegulationHistory(String username, String regulationId, String title, String action);

    Boolean deleteRegulationHistory(String username, Long id);

    Boolean isRegulationFavorite(String username, String regulationId);
}
