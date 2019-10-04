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
    public Book get(long id) {
        return null;
    }

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean remove(String name) {
        return false;
    }

    @Override
    public Book[] getAll(String authorName) {
        return null;
    }

    @Override
    public Book[] getAll(Date publishDate) {
        return null;
    }

}
