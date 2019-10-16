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

    /**
     * Должен получать книгу по Id из репозитория.
     * @param id
     * @return Книгу если найдена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public Book get(long id) {
        return null; //TODO
    }

    /**
     * Должен получать добавлять книгу в репозиторий.
     * @param book
     * @return true если добавлена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public boolean add(Book book) {
        return false; //TODO
    }

    /**
     * Должен удалять книгу по названию из репозитория.
     * @param name
     * @return true если удалена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public boolean remove(String name) {
        return false; //TODO
    }

    /**
     * Должен получать все книги автора из репозитория.
     * @param authorName
     * @return Книги если найдены. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public Book[] getAll(String authorName) {
        return null; //TODO
    }

    /**
     * Должен получать книгу по Id из репозитория.
     * @param publishDate
     * @return Книги если найдены. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public Book[] getAll(Date publishDate) {
        return null; //TODO
    }

}
