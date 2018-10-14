package ru.alfabank.bankinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alfabank.bankinfo.converters.BookConverter;
import ru.alfabank.bankinfo.gateway.OpenLibGateWay;
import ru.alfabank.model.BookThrift;
import ru.alfabank.thrift.OpenLibraryService;
import ru.trylogic.spring.boot.thrift.annotation.ThriftController;

import java.util.List;
import java.util.stream.Collectors;

@ThriftController("/library")
public class OpenLibraryThriftHandler implements OpenLibraryService.Iface {

    @Autowired
    private OpenLibGateWay libGateWay;

    @Autowired
    BookConverter converter;

    @Override
    public List<BookThrift> getBooks(String query) {
        return libGateWay
                .getBooks(query)
                .stream()
                .map(converter::mapToBookThrift)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookThrift> getBooksByAuthor(String author) {
        return libGateWay
                .getBooksByAuthor(author)
                .stream()
                .map(converter::mapToBookThrift)
                .collect(Collectors.toList());
    }
}
