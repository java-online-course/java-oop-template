package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[]{};


    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooksBuffer = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, schoolBooksBuffer, 0, schoolBooks.length);
        schoolBooksBuffer[schoolBooks.length] = (SchoolBook) book;
        schoolBooks = schoolBooksBuffer;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] results;
        int bookCount = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                bookCount++;
            }
        }
        results = new SchoolBook[bookCount];
        if (bookCount == 0) {
            return results;
        }
        bookCount = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                results[bookCount] = book;
                bookCount++;
            }
        }
        return results;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;
                counter++;
            }
        }
        SchoolBook[] schoolBooksBuffer = new SchoolBook[schoolBooks.length - counter];
        counter = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i] != null) {
                schoolBooksBuffer[counter] = schoolBooks[i];
                counter++;
            }
        }
        schoolBooks = schoolBooksBuffer;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}


