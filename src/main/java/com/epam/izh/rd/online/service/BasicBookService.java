package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;

public interface BasicBookService<T extends Book> {

    Author[] getAllAuthors();

    T getBiggestBook();

    T getBookForReading(String name);

    T[] getBookWithAuthor(Author author);

    T[] getBooksFromLastCentury();

    boolean putBook(T book);
}
