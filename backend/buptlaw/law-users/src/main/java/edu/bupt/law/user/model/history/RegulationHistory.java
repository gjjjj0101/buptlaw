package edu.bupt.law.user.model.history;

import edu.bupt.law.user.model.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.SpringApplication;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@Accessors(chain = true)
public class RegulationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String regulationId;

    private String title;

    private String action;

    private LocalDateTime createTime;
}
