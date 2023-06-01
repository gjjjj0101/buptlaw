package edu.bupt.buptlaw.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExceptionResponseBody {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private Integer message;

    private String url;

}
