package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;

public class SimpleSchoolBookService implements BookService {
    @Override
    public boolean save(Book book) {
        return false;
    }

    @Override
    public Book[] findByName(String name) {
        return new Book[0];
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return 0;
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Author findAuthorByBookName(String name) {
        return null;
    }
}
