package ru.alfabank.bankinfo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.bankinfo.gateway.OpenLibGateWay;
import ru.alfabank.bankinfo.model.Book;

import java.util.List;

@RestController
public class OpenLibraryController {

    @Autowired
    OpenLibGateWay openLibGateWay;

    @RequestMapping("/booksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam("author") String author) {
        return  openLibGateWay.getBooksbyAuthor(author).blockingFirst();
    }

    @RequestMapping("/books")
    public List<Book> getBooksByQuery(@RequestParam("query") String query) {
        return  openLibGateWay.getByQuery(query).blockingFirst();
    }

}
