package com.example.CrudApplication.repo;

import com.example.CrudApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
