package edu.bupt.law.user.controller.request;

import lombok.Data;

@Data
public class RegulationHistoryRequest {

    String regulationId;

    String title;

    String action;

}
