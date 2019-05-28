package com.example.fortnitenews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsApi {

    @Headers(
            "Authorization: d9b5badda943a9e87a6f587e5e005c36"
    )

    @GET("br_motd/get")
    Call<News> loadNews();
}

