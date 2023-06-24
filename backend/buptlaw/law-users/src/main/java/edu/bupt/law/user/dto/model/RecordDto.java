package edu.bupt.law.user.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RecordDto {
    Long recordId;
    Long userId;
    String title;
    String description;
    String content;
    String type;
    Integer status;
    LocalDateTime createDateTime;
    LocalDateTime updateDateTime;
    LocalDateTime deleteDateTime;
}
