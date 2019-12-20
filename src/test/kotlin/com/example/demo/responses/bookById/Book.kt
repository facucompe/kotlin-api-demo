package com.example.demo.responses.bookById

import com.example.demo.enumerators.Genre

class Book(
        var id : Int? = null,
        var name : String? = null,
        var genre : Genre? = null,
        var pageCount : Int? = null
)
