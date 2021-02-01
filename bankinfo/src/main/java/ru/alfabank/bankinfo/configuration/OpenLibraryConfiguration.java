package ru.alfabank.bankinfo.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alfabank.bankinfo.retrofit.OpenLibraryApi;

@Configuration
public class OpenLibraryConfiguration {

    @Value("${openLibAddress}")
    private String openLibAddress;

    @Bean
    OpenLibraryApi getOpenLibraryApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(openLibAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(OpenLibraryApi.class);
    }


}
