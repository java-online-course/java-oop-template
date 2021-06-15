package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        int size = schoolBooks.length + 1;
        SchoolBook[] newSchoolBooks = new SchoolBook[size];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, schoolBooks.length);
        newSchoolBooks[schoolBooks.length] = (SchoolBook) book;
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findedBooks = new SchoolBook[0];
        for (SchoolBook book : schoolBooks)
            if (name.equals(book.getName())) {
                SchoolBook[] newBooks = new SchoolBook[findedBooks.length + 1];
                System.arraycopy(findedBooks, 0, newBooks, 0, findedBooks.length);
                newBooks[findedBooks.length] = book;
                findedBooks = newBooks;
            }
        return findedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        Book[] removedBooks = findByName(name);
        if (removedBooks.length == 0) {
            return false;
        }
        SchoolBook[] newSchoolBooks = new SchoolBook[schoolBooks.length - removedBooks.length];
        int index = 0;
        for (SchoolBook book : schoolBooks) {
            boolean isDeleted = false;
            for (Book removedBook : removedBooks) {
                if (book.equals(removedBook)) {
                    isDeleted = true;
                    break;
                }
            }
            if (!isDeleted) {
                newSchoolBooks[index] = book;
                index++;
            }
        }
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
