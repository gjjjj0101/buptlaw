package edu.bupt.law.user.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CaseReasonNumsDto {

    String caseReason;

    Integer num;
}
