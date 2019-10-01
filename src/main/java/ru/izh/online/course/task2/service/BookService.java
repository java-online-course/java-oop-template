package ru.izh.online.course.task2.service;

import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

public interface BookService {

    boolean get(long id);
    boolean add(Book book);
    boolean remove(Book book);
    Book[] getAll(String authorName);
    Book[] getAll(Date publishDate);

}
