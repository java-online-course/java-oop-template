package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooks1 = new SchoolBook[count()];
        System.arraycopy(schoolBooks, 0, schoolBooks1, 0, count());
        schoolBooks = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks1, 0, schoolBooks, 0, schoolBooks1.length);
        schoolBooks[count() - 1] = book;
        return true;

    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = {};
        for (SchoolBook bookInArray : schoolBooks) {
            if (bookInArray.getName().equals(name)) {
                books = Arrays.copyOf(books, books.length + 1);
                books[books.length - 1] = bookInArray;
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        SchoolBook[] fieldForCopy = new SchoolBook[count() - findByName(name).length];
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName() != name) {
                fieldForCopy[i] = schoolBooks[i];

            }
        }
        schoolBooks = fieldForCopy;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
