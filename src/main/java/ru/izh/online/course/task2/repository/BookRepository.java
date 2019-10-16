package ru.izh.online.course.task2.repository;

import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

public interface BookRepository {

    Book get(long id);

    boolean add(Book book);

    boolean remove(String name);

    Book[] getAll(String authorName);

    Book[] getAll(Date publishDate);

}
