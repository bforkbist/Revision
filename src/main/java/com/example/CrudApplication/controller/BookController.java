package com.example.CrudApplication.controller;

import com.example.CrudApplication.model.Book;
import com.example.CrudApplication.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        try{
            List<Book> bookList = new ArrayList<>();
            bookRepo.findAll().forEach(bookList::add);
            if (bookList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookData = bookRepo.findById(id);
        if(bookData.isPresent()){
        return new ResponseEntity<Book>(bookData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookobj =bookRepo.save(book);
        return new ResponseEntity<>(bookobj, HttpStatus.OK);
    }

    @PostMapping("updateBookById/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book newBookData){
        Optional<Book> oldBookData = bookRepo.findById(id);
        if(oldBookData.isPresent()){
            Book updateBookData =oldBookData.get();
            updateBookData.setName(newBookData.getName());
            updateBookData.setAuthor(newBookData.getAuthor());
            Book bookObj = bookRepo.save(updateBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id){
        Optional<Book> bookData = bookRepo.findById(id);
        if(bookData.isPresent()){
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
