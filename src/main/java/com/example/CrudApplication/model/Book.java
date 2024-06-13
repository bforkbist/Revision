package com.example.CrudApplication.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String author;
}
