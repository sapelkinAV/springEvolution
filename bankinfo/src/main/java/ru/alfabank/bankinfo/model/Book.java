package ru.alfabank.bankinfo.model;


import lombok.Data;

import java.util.List;

@Data
public class Book {
    int cover_i;
    boolean has_fulltext;
    int edition_count;
    String title;
    List<String> author_name;
    int first_publish_year;
    String key;
    List<String> ia;
    String author_key;
    boolean public_scan_b;

}
