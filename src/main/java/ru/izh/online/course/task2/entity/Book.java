package ru.izh.online.course.task2.entity;

import java.time.LocalDate;

public class Book {

    private long id;
    private String name;
    private int pages;
    private Author author;
    private LocalDate publishDate;

    public Book(long id, String name, int pages, Author author, LocalDate publishDate) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.author = author;
        this.publishDate = publishDate;
    }
}
