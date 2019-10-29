package com.example.demo.DTO;

import com.example.demo.enumerators.Genre;

public class BookInput {
    private String name;
    private Genre genre;
    private Integer pageCount;

    public BookInput() {
    }

    public BookInput(String name, Genre genre, Integer pageCount) {
        this.name = name;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
