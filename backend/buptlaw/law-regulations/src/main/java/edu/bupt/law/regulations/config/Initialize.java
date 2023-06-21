package edu.bupt.law.regulations.config;


import edu.bupt.law.regulations.domain.Regulation;
import edu.bupt.law.regulations.repository.RegulationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Configuration
public class Initialize {

    @Bean
    CommandLineRunner init(RegulationRepository regulationRepository){
        return args -> {
            Set<Regulation> regulation = regulationRepository.findByTitle("全国人民代表大会常务委员会关于取缔邪教组织、防范和惩治邪教活动的决定");
            if (regulation != null){
                System.out.println(regulation.size());
            }
        };
    }
}
