package edu.bupt.law.regulations.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(indexName = "regulations")
public class Regulation {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Keyword)
    private String level;

    @Field(type = FieldType.Keyword)
    private Set<String> type;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private String office;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date publishTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date expiryTime;


}
