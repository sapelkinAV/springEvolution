package ru.alfabank.bankinfo.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alfabank.bankinfo.model.LibraryResponse;

public interface OpenLibraryApi {
    @GET("search.json")
    Observable<LibraryResponse> getBooksByQuery(@Query("q") String query);

    @GET("search.json")
    Observable<LibraryResponse> getBooksByAuthor(@Query("author") String author);

}
