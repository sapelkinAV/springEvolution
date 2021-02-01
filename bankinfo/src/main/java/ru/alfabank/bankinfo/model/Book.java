package ru.alfabank.bankinfo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Book {

    @JsonProperty("cover_i")
    int coverIndex;
    @JsonProperty("has_fulltext")
    boolean hasFulltext;
    @JsonProperty("edition_count")
    int editionCount;
    @JsonProperty("title")
    String title;
    @JsonProperty("author_name")
    List<String> authorName;
    @JsonProperty("first_publish_year")
    int firstPublishYear;
    @JsonProperty("key")
    String key;
    @JsonProperty("ia")
    List<String> ia;
    @JsonProperty("author_key")
    String authorKey;
    @JsonProperty("public_scan_b")
    boolean isPublicScanned;


}
