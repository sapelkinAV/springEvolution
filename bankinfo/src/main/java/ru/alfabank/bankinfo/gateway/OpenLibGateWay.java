package ru.alfabank.bankinfo.gateway;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.bankinfo.model.Book;
import ru.alfabank.bankinfo.retrofit.OpenLibraryApi;

import java.util.List;


@Component
public class OpenLibGateWay  {

    @Autowired
    private OpenLibraryApi openLibraryApi;


    public List<Book> getBooks(String query){
        return openLibraryApi
                .getBooksByQuery(query)
                .blockingFirst()
                .getDocs();
    }

    public List<Book> getBooksByAuthor(String author){
        return openLibraryApi
                .getBooksByAuthor(author)
                .blockingFirst()
                .getDocs();
    }

}