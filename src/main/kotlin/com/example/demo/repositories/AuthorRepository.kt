package com.example.demo.repositories

import com.example.demo.models.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Int>