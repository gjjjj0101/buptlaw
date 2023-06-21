package edu.bupt.law.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExceptionResponseBody {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private Integer code;
    private String message;

}
