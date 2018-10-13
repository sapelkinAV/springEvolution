package ru.alfabank.bankinfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LibraryResponse {

    @JsonProperty("start")
    int start;
    @JsonProperty("num_found")
    int numFound;
    @JsonProperty("docs")
    List<Book> docs;

}
