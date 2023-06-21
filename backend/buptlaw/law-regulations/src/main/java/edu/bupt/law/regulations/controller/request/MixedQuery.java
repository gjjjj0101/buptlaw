package edu.bupt.law.regulations.controller.request;

import lombok.Data;

import java.util.Locale;
import java.util.Set;

@Data
public class MixedQuery {

    String content;

    Boolean title;

    Boolean html;

    Boolean isExactly;

    Set<String> office;

    Set<String> status;

    Set<String> level;

    Set<String> lawClass;

    Set<String> officeClass;

    Long publishDateStart;

    Long publishDateEnd;

    Long expiryStart;

    Long expiryEnd;

}
