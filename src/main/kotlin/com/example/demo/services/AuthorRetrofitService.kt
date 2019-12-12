package com.example.demo.services

import com.example.demo.DTO.AuthorApiRespose
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AuthorRetrofitService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("authors/{authorId}")
    fun getAuthor(@Path("authorId") authorId : Int): Call<AuthorApiRespose>

}