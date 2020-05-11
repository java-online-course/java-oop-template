package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository <SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] savedSchoolBook = new SchoolBook[count() + 1];
        if (count() > 0) {
            System.arraycopy(schoolBooks, 0, savedSchoolBook, 0, count());
        }
        savedSchoolBook[count()] = book;
        schoolBooks = savedSchoolBook;
        return true;
    }

    @Override
    public Book[] findByName(String name) {
        int amountOfBooks = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                amountOfBooks++;
            }
        }
        if (amountOfBooks != 0) {
            SchoolBook[] foundBook = new SchoolBook[amountOfBooks];
            int indexOfBook = 0;
            for (SchoolBook book : schoolBooks) {
                if (Arrays.asList(schoolBooks).contains(book)) {
                    foundBook[indexOfBook] = book;
                    indexOfBook++;
                }
            }
            return foundBook;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int amountOfBooks = findByName(name).length;
        if (amountOfBooks == 0) {
            return false;
        }
        SchoolBook[] removedBooks = new SchoolBook[count() - amountOfBooks];
        int indexOfBook = 0;
        for (SchoolBook book : schoolBooks) {
            if (!book.getName().equals(name)) {
                removedBooks[indexOfBook] = book;
                indexOfBook++;
            }
        }
        schoolBooks = removedBooks;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
