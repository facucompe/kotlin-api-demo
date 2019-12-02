package com.example.demo.models

import com.example.demo.services.AuthorService
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "author")
class Author(
    @NotNull
    @Column(name = "first_name")
    var firstName : String?,

    @NotNull
    @Column(name = "last_name")
    var lastName : String?,

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "author")
    var books : List<Book>?,

    @Column(name = "external_id")
    var externalId : Int?,

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id : Int? = null
) {
    constructor() : this(null, null, null, null, null)

    fun spotlight(): String {
        return AuthorService().getAuthor(externalId!!).spotlight
    }
}