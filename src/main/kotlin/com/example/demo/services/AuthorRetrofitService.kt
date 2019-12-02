package com.example.demo.services

import com.example.demo.DTO.AuthorApiRespose
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Headers
import retrofit.http.Path

interface AuthorRetrofitService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("authors/{authorId}")
    fun getAuthor(@Path("authorId") authorId : Int): Call<AuthorApiRespose>

}