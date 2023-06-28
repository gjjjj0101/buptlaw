package edu.bupt.law.regulations.service.impl;

import edu.bupt.law.regulations.controller.request.MixedQuery;
import edu.bupt.law.regulations.domain.Regulation;
import edu.bupt.law.regulations.exception.RegulationNotFound;
import edu.bupt.law.regulations.repository.RegulationRepository;
import edu.bupt.law.regulations.service.RegulationService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RegulationServiceImpl implements RegulationService {

    @Autowired
    RegulationRepository regulationRepository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;


    @Override
    public void addRegulation() {

    }

    @Override
    public Set<Regulation> findByLevel(String level) {
        return regulationRepository.findByLevel(level);
    }

    @Override
    public Page<Regulation> findByLevel(String level, Pageable pageable) {
        return regulationRepository.findByLevel(level, pageable);
    }

    @Override
    public Page<Regulation> findByLawClass1(String lawClass1, Pageable pageable) {
        return regulationRepository.findByLawClass1(lawClass1, pageable);
    }

    @Override
    public Page<Regulation> findByOffice(String office, Pageable pageable) {
        Page<Regulation> regulations = regulationRepository.findByOffice(office, pageable);
        return regulations;
    }

    @Override
    public Set<Regulation> findByOffice(String office) {
        return regulationRepository.findByOffice(office);
    }

    @Override
    public Regulation findById(String id) {
        return regulationRepository.findById(id).orElseThrow(() -> new RegulationNotFound(id)
        );
    }

    @Override
    public SearchHits<Regulation> mixedSearch(MixedQuery mixedQuery, Pageable pageable) {

        ArrayList<QueryBuilder> searchQueryList = new ArrayList<>();
        ArrayList<QueryBuilder> filterQueryList = new ArrayList<>();
        ArrayList<QueryBuilder> rangeQueryList = new ArrayList<>();

        if(mixedQuery.getContent() != null && !mixedQuery.getContent().equals("")) {
            if(mixedQuery.getIsExactly()){
                if (mixedQuery.getTitle() != null && mixedQuery.getTitle()){
                    QueryBuilder search = QueryBuilders.wildcardQuery("title", "*" + mixedQuery.getContent() + "*");
                    searchQueryList.add(search);
                }
                if (mixedQuery.getHtml() != null && mixedQuery.getHtml()){
                    QueryBuilder search = QueryBuilders.wildcardQuery("html", "*" + mixedQuery.getContent() + "*");
                    searchQueryList.add(search);
                }
            }else{
                if (mixedQuery.getTitle() != null && mixedQuery.getTitle()){
                    QueryBuilder search = QueryBuilders.matchQuery("title", mixedQuery.getContent());
                    searchQueryList.add(search);
                }
                if (mixedQuery.getHtml() != null && mixedQuery.getHtml()){
                    QueryBuilder search = QueryBuilders.matchQuery("html", mixedQuery.getContent());
                    searchQueryList.add(search);
                }
            }
        }

        if(mixedQuery.getOffice() != null){
            QueryBuilder filter = QueryBuilders.termsQuery("office", mixedQuery.getOffice().toArray(new String[0]));
            filterQueryList.add(filter);
        }

        if(mixedQuery.getLevel() != null){
            QueryBuilder filter = QueryBuilders.termsQuery("level", mixedQuery.getLevel().toArray(new String[0]));
            filterQueryList.add(filter);
        }

        if (mixedQuery.getPublishDateStart() != null || mixedQuery.getPublishDateEnd() != null){
            QueryBuilder range = QueryBuilders.rangeQuery("date").from(mixedQuery.getPublishDateEnd(),true).to(mixedQuery.getPublishDateEnd(),true);
            rangeQueryList.add(range);
        }

        if (mixedQuery.getExpiryStart() != null || mixedQuery.getExpiryEnd() != null){
            QueryBuilder range = QueryBuilders.rangeQuery("expiry").from(mixedQuery.getExpiryStart(),true).to(mixedQuery.getExpiryEnd(),true);
            rangeQueryList.add(range);
        }

        if (mixedQuery.getLawClass() != null){
            ArrayList<String> class1 = new ArrayList<>();
            ArrayList<String> class2 = new ArrayList<>();
            for(String lawClass : mixedQuery.getLawClass()){
                if(lawClass.endsWith("00")){
                    class1.add(lawClass);
                }else{
                    class2.add(lawClass);
                }
            }
            if (!class1.isEmpty()){
                QueryBuilder filter = QueryBuilders.termsQuery("law_class_1", class1.toArray(new String[0]));
                filterQueryList.add(filter);
            }
            if (!class2.isEmpty()){
                QueryBuilder filter = QueryBuilders.termsQuery("law_class_2", class2.toArray(new String[0]));
                filterQueryList.add(filter);
            }
        }

        if (mixedQuery.getOfficeClass() != null){
            ArrayList<String> class1 = new ArrayList<>();
            ArrayList<String> class2 = new ArrayList<>();
            for(String officeClass : mixedQuery.getOfficeClass()){
                if(officeClass.endsWith("00")){
                    class1.add(officeClass);
                }else{
                    class2.add(officeClass);
                }
            }
            if (!class1.isEmpty()){
                QueryBuilder filter = QueryBuilders.termsQuery("office_class_1", class1.toArray(new String[0]));
                filterQueryList.add(filter);
            }
            if (!class2.isEmpty()){
                QueryBuilder filter = QueryBuilders.termsQuery("office_class_2", class2.toArray(new String[0]));
                filterQueryList.add(filter);
            }
        }

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!searchQueryList.isEmpty()){
            for (QueryBuilder queryBuilder : searchQueryList){
                boolQueryBuilder.must(queryBuilder);
            }
        }

        if (!filterQueryList.isEmpty()){
            for (QueryBuilder queryBuilder : filterQueryList){
                boolQueryBuilder.filter(queryBuilder);
            }
        }

        if (!rangeQueryList.isEmpty()){
            for (QueryBuilder queryBuilder : rangeQueryList){
                boolQueryBuilder.filter(queryBuilder);
            }
        }
        Query query = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build().setPageable(pageable);
        SearchHits<Regulation> page = elasticsearchOperations.search(query, Regulation.class);
        return page;
    }
}
