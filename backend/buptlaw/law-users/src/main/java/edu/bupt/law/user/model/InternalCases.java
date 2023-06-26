package edu.bupt.law.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@Accessors(chain = true)
public class InternalCases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    String name;

    String reason;

    String description;

    String Type;

    String content;

    String status;

    LocalDateTime openTime;

    LocalDateTime createTime;

    LocalDateTime updateTime;

    LocalDateTime deleteTime;
}
