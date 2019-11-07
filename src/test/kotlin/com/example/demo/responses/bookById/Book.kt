package com.example.demo.responses.bookById

import com.example.demo.enumerators.Genre

class Book(
        var id : Int?,
        var name : String?,
        var genre : Genre?,
        var pageCount : Int?
) {
    constructor() : this(null,null, null, null)
}
