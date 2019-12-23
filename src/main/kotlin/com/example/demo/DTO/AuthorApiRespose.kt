package com.example.demo.DTO

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthorApiRespose(
        @SerializedName("spotlight")
        var spotlight: String
) : Serializable
