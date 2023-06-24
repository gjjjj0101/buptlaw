package edu.bupt.law.user.model.history;

import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@Accessors(chain = true)
public class RecordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Record record;

    private String action;

    private LocalDateTime createTime;
}
