package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        if (schoolBooks == null) {
            schoolBooks = new SchoolBook[1];
            schoolBooks[0] = book;
        } else {
            SchoolBook[] arrayCopy = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
            arrayCopy[arrayCopy.length - 1] = book;
            schoolBooks = arrayCopy;
        }
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[0];
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                SchoolBook[] arrayCopy = Arrays.copyOf(books, books.length + 1);
                arrayCopy[arrayCopy.length - 1] = book;
                books = arrayCopy;
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        } else {
            SchoolBook[] newArr = new SchoolBook[schoolBooks.length - findByName(name).length];
            if (newArr.length == 0) {
                schoolBooks = new SchoolBook[0];
                return true;
            }
            int n = 0;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equals(findByName(name)[0])) {
                    n++;
                    continue;
                } else {
                    newArr[i - n] = schoolBooks[i];
                }
            }
            schoolBooks = newArr;
        }
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
