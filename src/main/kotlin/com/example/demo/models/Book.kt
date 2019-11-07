package com.example.demo.models

import com.example.demo.enumerators.Genre
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "book")
class Book (
        @Column(name = "name")
        var name: String?,

        @Column(name = "page_count")
        var pageCount: Int?,

        @Column(name = "genre")
        var genre : Genre?,

        @ManyToOne
        @JoinColumn(name = "author_id")
        var author : Author? = null,

        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id : Int? = null
) {
        constructor() : this(null,null, null, null, null)
}