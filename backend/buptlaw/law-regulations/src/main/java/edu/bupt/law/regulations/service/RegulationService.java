package edu.bupt.law.regulations.service;

import edu.bupt.law.regulations.controller.request.MixedQuery;
import edu.bupt.law.regulations.domain.Regulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;
import java.util.Set;

public interface RegulationService {
    void addRegulation();
    Set<Regulation> findByLevel(String level);

    Page<Regulation> findByLevel(String level, Pageable pageable);

    Page<Regulation> findByLawClass1(String lawClass1, Pageable pageable);

    Page<Regulation> findByOffice(String office, Pageable pageable);

    Set<Regulation> findByOffice(String office);

    Regulation findById(String id);

    SearchHits<Regulation> mixedSearch(MixedQuery query, Pageable pageable);
}
