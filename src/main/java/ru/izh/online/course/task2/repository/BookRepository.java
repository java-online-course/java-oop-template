package ru.izh.online.course.task2.repository;

import ru.izh.online.course.task2.entity.Book;

import java.util.Date;

public interface BookRepository {

    /**
     * Должен возвращать сущность типа книга по его id, если такой книги нет, то возвратить null.
     * @param id ид книги.
     * @return удалось добавить книгу или нет.
     */
    Book get(long id);

    /**
     * Должен добавлять в массив книг новую книгу.
     * @param book сущность книга.
     * @return true если книга была добавлена.
     */
    boolean add(Book book);

    /**
     * Должен удалять из массива книг книгу по названию.
     * @param name название книги
     * @return true если книга была удалена
     */
    boolean remove(String name);

    /**
     * Должен возвращать из массива книг все книги с автором authorName
     * @param authorName
     * @return Массив книг
     */
    Book[] getAll(String authorName);

    /**
     * Должен возвращать из массива книг все книги которые были изданы в publishDate
     * @param publishDate
     * @return Массив книг
     */
    Book[] getAll(Date publishDate);

}
