package ru.alfabank.bankinfo.gateway;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Flowable;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.http.HttpClient;
import io.vertx.reactivex.core.http.HttpClientRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alfabank.bankinfo.model.Book;

import java.util.ArrayList;
import java.util.Arrays;


@Component
public class OpenLibGateWay  {

    @Value("${openLibAddress}")
    private String openLibAddress;

    private final String BOOK_TAG_NAME = "docs";



    public Flowable<ArrayList<Book>> getBooksbyAuthor(String author)  {
        HttpClient client = Vertx.vertx().createHttpClient();
        HttpClientRequest req = client
                .request(HttpMethod.GET,
                        openLibAddress + "search.json?author=" + author.replaceAll(" ", "+"));

        return getArrayListFlowable(req);
    }

    public Flowable<ArrayList<Book>> getByQuery(String query)  {
        HttpClient client = Vertx.vertx().createHttpClient();
        HttpClientRequest req = client
                .request(HttpMethod.GET,
                        openLibAddress + "search.json?q=" + query.replaceAll(" ", "+"));

        return getArrayListFlowable(req);
    }

    private Flowable<ArrayList<Book>> getArrayListFlowable(HttpClientRequest req) {
        return req
                .toFlowable()
                .flatMap(resp -> {
                    if (resp.statusCode() != 200) {
                        throw new RuntimeException("Wrong status code " + resp.statusCode());
                    }
                    return Flowable.just(Buffer.buffer()).mergeWith(resp.toFlowable());
                })
                .map(Buffer::toString)
                .map(jsonString->{
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode node = mapper.valueToTree(jsonString);
                    Book[] books = mapper.readValue(node.get(BOOK_TAG_NAME).asText(), Book[].class);
                    return new ArrayList<>(Arrays.asList(books));});
    }


}