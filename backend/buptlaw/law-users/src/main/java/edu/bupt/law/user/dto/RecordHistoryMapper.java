package edu.bupt.law.user.dto;

import edu.bupt.law.user.dto.model.RecordHistoryDto;
import edu.bupt.law.user.model.history.RecordHistory;
import org.springframework.stereotype.Component;

@Component
public class RecordHistoryMapper {

    public RecordHistoryDto toRecordHistoryDto(RecordHistory recordHistory) {
        RecordHistoryDto recordHistoryDto = new RecordHistoryDto();
        recordHistoryDto.setId(recordHistory.getId())
                .setRecordId(recordHistory.getRecord().getId())
                .setUserId(recordHistory.getUser().getId())
                .setTitle(recordHistory.getRecord().getTitle())
                .setAction(recordHistory.getAction())
                .setCreateTime(recordHistory.getCreateTime());
        return recordHistoryDto;
    }
}
