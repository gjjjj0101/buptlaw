package edu.bupt.law.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table
@Accessors(chain = true)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String type;

    private Integer status;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private LocalDateTime deleteDateTime;
}
