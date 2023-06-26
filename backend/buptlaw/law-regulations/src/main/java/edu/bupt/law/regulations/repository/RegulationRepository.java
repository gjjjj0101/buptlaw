package edu.bupt.law.regulations.repository;

import edu.bupt.law.regulations.domain.Regulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;
import java.util.Set;


public interface RegulationRepository extends ElasticsearchRepository<Regulation, String> {

    Optional<Regulation> findById(String id);

    Set<Regulation> findByTitle(String title);

    Page<Regulation> findByTitle(String title, Pageable page);

    Page<Regulation> findByLevel(String level, Pageable page);

    Page<Regulation> findByOffice(String office, Pageable page);

    Page<Regulation> findByLawClass1(String lawClass1, Pageable page);

    Set<Regulation> findByLevel(String level);

    Set<Regulation> findByOffice(String office);
}
