package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBook = new SchoolBook[schoolBooks.length + 1];
        for (int i = 0; i < schoolBooks.length; i++) {
            newSchoolBook[i] = schoolBooks[i];
        }
        newSchoolBook[newSchoolBook.length - 1] = book;
        schoolBooks = newSchoolBook;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int counter = 0;

        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                counter++;
            }
        }
        SchoolBook[] newSchoolBooks = new SchoolBook[counter];
        int interator = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                newSchoolBooks[interator] = schoolBooks[i];
                interator++;
            }
        }
        return newSchoolBooks;
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
        SchoolBook[] tmpSchoolBooks = new SchoolBook[schoolBooks.length - counter];
        int iterator = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i] != null) {
                tmpSchoolBooks[iterator] = schoolBooks[i];
                iterator++;
            }
        }
        schoolBooks = tmpSchoolBooks;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
