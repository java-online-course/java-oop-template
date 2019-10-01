package ru.izh.online.course.task2.repository;

import ru.izh.online.course.task2.entity.Author;
import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

import static java.time.LocalDate.of;

/**
 * Репозиторий книг. Имеет внутри себя массив книг и методы для работы с ним. ID генерируется методом getNextId
 */
public class SimpleBookRepository implements BookRepository {

    private static long bookIdHolder;

    public static long getNextId() {
        return bookIdHolder++;
    }

    private static final Author[] AUTHORS = {
            new Author(1, "Joshua Bloch"),
            new Author(2, "Doug Lea"),
            new Author(3, "Thomas H. Cormen"),

    };

    private Book[] books = {
            new Book(getNextId(), "Effective Java Programming", 412, AUTHORS[0], of(2018, 1 ,6)),
            new Book(getNextId(), "Java Concurrency in Practice", 432 , AUTHORS[1], of(2006, 5, 19)),
            new Book(getNextId(), "Concurrent Programming in Java: Design Principles and Patterns", 339 , AUTHORS[1], of(1997, 10, 25)),
            new Book(getNextId(), "Introduction to Algorithms", 1292  , AUTHORS[2], of(2009, 7, 31))
    };

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
