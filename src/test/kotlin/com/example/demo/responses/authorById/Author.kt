package com.example.demo.responses.authorById

import com.example.demo.responses.bookById.Book

data class Author(
        var id : Int? = null,
        var firstName : String? = null,
        var lastName : String? = null,
        var books : List<Book>? = null
)
