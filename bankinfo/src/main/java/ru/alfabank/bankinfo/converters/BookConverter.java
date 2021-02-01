package ru.alfabank.bankinfo.converters;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.alfabank.bankinfo.model.Book;
import ru.alfabank.model.BookThrift;

@Mapper(componentModel = "spring")
public interface BookConverter {

    @Mappings({
        @Mapping(source = "title",target = "title"),
        @Mapping(source = "authorName",target = "authors" ),
        @Mapping(source = "firstPublishYear",target = "firstPublishYear")

    })
    BookThrift mapToBookThrift(Book book);

}
