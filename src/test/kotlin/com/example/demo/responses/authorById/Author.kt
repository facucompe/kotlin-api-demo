package com.example.demo.responses.authorById

import com.example.demo.responses.bookById.Book

class Author(
        var id : Int?,
        var firstName : String?,
        var lastName : String?,
        var books : List<Book>?
) {
    constructor() : this(null, null, null, null)
}
