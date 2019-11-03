package ru.izh.online.course.repository;

import ru.izh.online.course.entity.Book;

/**
 * Базовый инерфейс для сущностей, который наследуют BasicEntity. Имеет стандартные операции поиска, вставки и удаления
 * @param <T>
 */
public interface BookRepository<T extends Book> {

    T get(long id);

    boolean add(T entity);

    boolean remove(String name);

    T[] getAll(String name);
}
