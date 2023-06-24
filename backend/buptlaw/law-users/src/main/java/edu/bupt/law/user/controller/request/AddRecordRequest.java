package edu.bupt.law.user.controller.request;

import lombok.Data;

@Data
public class AddRecordRequest {

    String title;

    String description;

    String content;

    String type;

}
