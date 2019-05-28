package com.example.fortnitenews;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<News> {

    ServerResponse handler;


    public Controller(ServerResponse handler) {
        this.handler = handler;

    }


    static final String BASE_URL = "https://fortnite-api.theapinetwork.com/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        NewsApi api = retrofit.create(NewsApi.class);

        Call<News> call = api.loadNews();
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        if (response.isSuccessful()) {
            News news = response.body();
            handler.onResponse(news);

        } else {
            Log.d("CONTROLLER", response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        t.printStackTrace();

    }

    interface ServerResponse {
        public void onResponse(News news);
    }

}