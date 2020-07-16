package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[0];

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                books = Arrays.copyOf(books, books.length + 1);
                books[books.length - 1] = schoolBook;
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                SchoolBook book = schoolBooks[i];
                schoolBooks[i] = schoolBooks[schoolBooks.length - 1];
                schoolBooks[schoolBooks.length - 1] = book;
                this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);

                if ((schoolBooks.length == 1) && schoolBooks[0].getName().equals(book.getName())) {
                    this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
