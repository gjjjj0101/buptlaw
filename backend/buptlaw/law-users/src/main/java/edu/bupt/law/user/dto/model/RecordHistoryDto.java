package edu.bupt.law.user.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RecordHistoryDto {
    Long id;
    Long userId;
    Long recordId;
    String title;
    String action;
    LocalDateTime createTime;
}
