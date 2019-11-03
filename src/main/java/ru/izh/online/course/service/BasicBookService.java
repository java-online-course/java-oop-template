package ru.izh.online.course.service;

import ru.izh.online.course.entity.Book;

public interface BasicBookService<T extends Book> {

    T get(long id);

    boolean add(T book);

    boolean remove(String name);

    T[] getAll(String name);
}
