package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.ArrayList;
import java.util.List;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooks = new SchoolBook[this.schoolBooks.length + 1];

        for (int i = 0; i < this.schoolBooks.length; i++) {
            schoolBooks[i] = this.schoolBooks[i];
        }

        schoolBooks[schoolBooks.length - 1] = book;
        this.schoolBooks = schoolBooks;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int numberOfBooksFound = 0;

        for (SchoolBook book: this.schoolBooks) {
            if (book.getName() == name) {
                numberOfBooksFound++;
            }
        }

        if (numberOfBooksFound == 0) {
            return new SchoolBook[0];
        }

        SchoolBook[] resultBooks = new SchoolBook[numberOfBooksFound];

        int i = 0;
        for (SchoolBook book: this.schoolBooks) {
            if (book.getName() == name) {
                resultBooks[i] = book;
                i++;
            }
        }

        return resultBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name) == null) {
            return false;
        }

        int numberOfFoundBooks = 0;
        for (SchoolBook book: this.schoolBooks) {
            if (book.getName() == name) {
                numberOfFoundBooks++;
            }
        }

        SchoolBook[] schoolBooks = new SchoolBook[this.schoolBooks.length - numberOfFoundBooks];

        int j = 0;
        for (int i = 0; i < this.schoolBooks.length; i++) {
            if (this.schoolBooks[i].getName() != name) {
                schoolBooks[j] = this.schoolBooks[i];
                j++;
            }
        }

        this.schoolBooks = schoolBooks;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
