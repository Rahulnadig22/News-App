package com.example.retrofit;

import java.util.HashMap;

import model.news;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("everything/")
    Call<news> getEveryNews(@QueryMap HashMap<String,Object> queries);

    @GET("top-headlines/")
    Call<news> getTopHeadlines(@QueryMap HashMap<String,Object> queries);
}
