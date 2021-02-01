package ru.alfabank.bankinfo.retrofit;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alfabank.bankinfo.model.LibraryResponse;

public interface OpenLibraryApi {
    @GET("search.json")
    Flowable<LibraryResponse> getBooksByQuery(@Query("q") String query);

    @GET("search.json")
    Flowable<LibraryResponse> getBooksByAuthor(@Query("author") String author);

}
