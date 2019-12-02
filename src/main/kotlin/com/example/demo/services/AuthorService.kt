package com.example.demo.services

import com.example.demo.DTO.AuthorApiRespose
import com.example.demo.exceptions.ExternalServiceError
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit

class AuthorService {

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://reststop.randomhouse.com/resources/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getAuthor(id: Int) : AuthorApiRespose {
        val retrofitService: AuthorRetrofitService = retrofit().create(AuthorRetrofitService::class.java)

        try {
            val authorCall: Response<AuthorApiRespose> = retrofitService.getAuthor(id).execute()

            if(authorCall.isSuccess) return authorCall.body()
            else throw ExternalServiceError("Author API error")
        } catch (e : Exception) {
            throw ExternalServiceError("Author API error")
        }
    }
}