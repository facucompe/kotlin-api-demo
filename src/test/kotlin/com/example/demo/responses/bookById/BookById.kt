package com.example.demo.responses.bookById

class BookById(var data : BookByIdData?, var errors : List<Error>?) {
    constructor() : this(null, null)
}
