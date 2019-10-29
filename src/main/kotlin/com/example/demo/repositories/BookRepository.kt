package com.example.demo.repositories

import com.example.demo.models.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int>
