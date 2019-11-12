package com.epam.izh.rd.online.entity;

import java.util.Date;

/**
 * Учебник. Кроме id, количества страниц и названия имеет автора и дату публикации
 */
public class Schoolbook extends Book {

    private Author author;
    private Date publishDate;

    public Schoolbook(long id, String name, int pages, Author author, Date publishDate) {
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
