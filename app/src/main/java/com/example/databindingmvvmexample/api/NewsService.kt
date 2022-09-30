package com.example.databindingmvvmexample.api

import com.example.databindingmvvmexample.models.NewsData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface NewsService {

    @GET(GET_EVERYTHING)
    suspend fun getNews(@Query("q") q : String, @Query("apiKey") apiKey : String) : Response<NewsData>

}