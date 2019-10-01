package ru.izh.online.course.task2.repository;

import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

public interface BookRepository {

    boolean get(long id);
    boolean add(Book book);
    boolean remove(Book book);
    Book[] getAll(String authorName);
    Book[] getAll(Date publishDate);

}
