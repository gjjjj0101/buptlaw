package edu.bupt.law.user.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CaseDto {
    Long caseId;

    Long userId;

    String caseName;

    String caseReason;

    String caseDescription;

    String caseType;

    String caseContent;

    String caseStatus;

    LocalDateTime openTime;

    LocalDateTime createTime;

    LocalDateTime updateTime;

    LocalDateTime deleteTime;
}
