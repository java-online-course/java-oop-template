package com.epam.izh.rd.online.repository;


import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] books;

    @Override
    public boolean save(SchoolBook book) {
        if (books != null) {
            books = Arrays.copyOf(books, books.length + 1);
            books[books.length - 1] = book;
        } else {
            books = new SchoolBook[1];
            books[0] = book;
        }
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (books != null) {
            SchoolBook[] schoolBooks = new SchoolBook[books.length];
            int i = 0;
            for (SchoolBook schoolBook : books) {
                if (schoolBook.getName().equals(name)) {
                    schoolBooks[i] = schoolBook;
                    i++;
                }
            } return Arrays.copyOf(schoolBooks, i);
        } else {
            return new SchoolBook[0];
        }
    }

    @Override
    public boolean removeByName(String name) {
        int count = 1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(name)) {
                books[i] = books[books.length - count];
                count++;
            }
        }
        if (count>1) {
            books = Arrays.copyOf(books, books.length - (count - 1));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return books.length;
    }
}
