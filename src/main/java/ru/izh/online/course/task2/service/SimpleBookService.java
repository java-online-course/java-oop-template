package ru.izh.online.course.task2.service;

import ru.izh.online.course.task2.entity.Book;
import ru.izh.online.course.task2.repository.BookRepository;
import ru.izh.online.course.task2.repository.SimpleBookRepository;

import java.util.Date;

/**
 * Сервис по работе с книгами. Должен вызывать bookRepository
 */
public class SimpleBookService implements BookService {

    private BookRepository bookRepository = new SimpleBookRepository();

    @Override
    public boolean get(long id) {
        return false;
    }

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean remove(Book book) {
        return false;
    }

    @Override
    public Book[] getAll(String authorName) {
        return new Book[0];
    }

    @Override
    public Book[] getAll(Date publishDate) {
        return new Book[0];
    }

}
