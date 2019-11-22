package com.example.demo.responses.authorById

class AuthorById(var data : AuthorByIdData?, var errors : List<Error>?) {
    constructor() : this(null, null)
}
