package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;

public class SimpleSchoolBookRepository implements BookRepository {
    @Override
    public boolean save(Book book) {
        return false;
    }

    @Override
    public Book[] findByName(String name) {
        return new Book[0];
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
