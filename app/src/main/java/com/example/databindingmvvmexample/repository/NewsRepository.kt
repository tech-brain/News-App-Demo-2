package com.example.databindingmvvmexample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingmvvmexample.api.API_KEY
import com.example.databindingmvvmexample.api.NewsService
import com.example.databindingmvvmexample.api.Response
import com.example.databindingmvvmexample.models.NewsData
import com.example.databindingmvvmexample.utils.NetworkUtils

class NewsRepository(private val newsService: NewsService, private val context: Context) {

    private val newsLiveData = MutableLiveData<Response<NewsData>>()
    val news: LiveData<Response<NewsData>> = newsLiveData

    suspend fun getNews(type: String) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {

                val result = newsService.getNews(type, API_KEY)
                if(result.isSuccessful){

                    if(result.body() != null) {
                        if (result.body()?.status == "ok") {
                            newsLiveData.postValue(Response.Success(result.body()))
                        }else{
                            newsLiveData.postValue(Response.Error(result.errorBody().toString()))
                        }
                    }else{
                        newsLiveData.postValue(Response.Error(result.errorBody().toString()))
                    }

                }else{

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}