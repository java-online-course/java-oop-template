package ru.izh.online.course.task2.service;

import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

public interface BookService {

    /**
     * Должен получать книгу по Id из репозитория.
     * @param id
     * @return Книгу если найдена. В противном случае печатать в консоль сообщение с ошибкой
     */
    Book get(long id);

    /**
     * Должен получать добавлять книгу в репозиторий.
     * @param book
     * @return true если добавлена. В противном случае печатать в консоль сообщение с ошибкой
     */
    boolean add(Book book);

    /**
     * Должен удалять книгу по названию из репозитория.
     * @param name
     * @return true если удалена. В противном случае печатать в консоль сообщение с ошибкой
     */
    boolean remove(String name);

    /**
     * Должен получать все книги автора из репозитория.
     * @param authorName
     * @return Книги если найдены. В противном случае печатать в консоль сообщение с ошибкой
     */
    Book[] getAll(String authorName);

    /**
     * Должен получать книгу по Id из репозитория.
     * @param publishDate
     * @return Книги если найдены. В противном случае печатать в консоль сообщение с ошибкой
     */
    Book[] getAll(Date publishDate);

}
