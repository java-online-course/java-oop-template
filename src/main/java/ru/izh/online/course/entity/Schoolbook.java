package ru.izh.online.course.entity;

import java.time.LocalDate;

/**
 * Учебник. Кроме id, количества страниц и названия имеет автора и дату публикации
 */
public class Schoolbook extends Book {

    private Author author;
    private LocalDate publishDate;

    public Schoolbook(long id, String name, int pages, Author author, LocalDate publishDate) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
