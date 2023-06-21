package edu.bupt.law.regulations.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Document(indexName = "regulations")
public class Regulation {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String p_id;
    @Field(type = FieldType.Keyword)
    private String pkey;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String html;

    @Field(type = FieldType.Keyword)
    private String level;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private Set<String> office;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Field(type = FieldType.Date, format = DateFormat.epoch_second)
    private Date publish;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Field(type = FieldType.Date, format = DateFormat.epoch_second)
    private Date expiry;

    @Field(type = FieldType.Keyword,name = "law_class_1")
    private Set<String> lawClass1;

    @Field(type = FieldType.Keyword,name = "law_class_2")
    private Set<String> lawClass2;

    @Field(type = FieldType.Keyword,name = "office_class_1")
    private Set<String> officeClass1;

    @Field(type = FieldType.Keyword,name = "office_class_2")
    private Set<String> officeClass2;

}
