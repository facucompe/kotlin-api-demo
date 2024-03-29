package com.example.demo.services

import com.example.demo.DTO.AuthorApiRespose
import com.example.demo.exceptions.ExternalServiceError
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthorService {

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://reststop.randomhouse.com/resources/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getAuthor(id: Int) : AuthorApiRespose {
        val retrofitService = retrofit().create(AuthorRetrofitService::class.java)

        try {
            val authorCall = retrofitService.getAuthor(id).execute()

            if(authorCall.isSuccessful) return authorCall.body()!!
            else throw ExternalServiceError("Author API error")
        } catch (e : Exception) {
            throw ExternalServiceError("Author API error")
        }
    }
}
