package edu.bupt.law.user.dto;

import edu.bupt.law.user.dto.model.RecordDto;
import edu.bupt.law.user.model.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {
    public RecordDto toRecordDto(Record record){
        RecordDto recordDto = new RecordDto();
        recordDto.setRecordId(record.getId()).setUserId(record.getUser().getId()).setTitle(record.getTitle())
                .setDescription(record.getDescription()).setContent(record.getContent())
                .setType(record.getType()).setStatus(record.getStatus())
                .setCreateDateTime(record.getCreateDateTime()).setUpdateDateTime(record.getUpdateDateTime())
                .setDeleteDateTime(record.getDeleteDateTime());
        return recordDto;
    }
}
