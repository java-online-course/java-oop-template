package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;

/**
 * Базовый инерфейс для сущностей, который наследуют Book. Имеет стандартные операции поиска, вставки и удаления
 * @param <T>
 */
public interface BookRepository<T extends Book> {

    T get(long id);

    boolean add(T book);

    boolean remove(String name);

    boolean isContains(T book);

    T[] getAll(String name);
}
