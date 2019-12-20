package com.example.demo.DTO

import com.example.demo.enumerators.Genre

data class BookInput(val name: String, val genre: Genre, val pageCount: Int)
