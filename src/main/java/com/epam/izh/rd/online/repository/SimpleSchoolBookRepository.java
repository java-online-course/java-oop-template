package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[1];


    @Override
    public boolean save(SchoolBook book) {
        if (schoolBooks[0] == null) {
            SchoolBook[] saveSchoolBooks = new SchoolBook[schoolBooks.length];
            saveSchoolBooks[0] = book;
            schoolBooks = saveSchoolBooks;
            return true;
        } else {
            SchoolBook[] secondSaveSchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
            for (int i = 0; i < secondSaveSchoolBooks.length; i++) {
                if (secondSaveSchoolBooks[i] == null)
                    secondSaveSchoolBooks[i] = book;
            }
            schoolBooks = secondSaveSchoolBooks;
            return true;
        }

    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findSchoolBooks = new SchoolBook[schoolBooks.length];
        int count = 0;

        if (schoolBooks[0] == null) {
            return findSchoolBooks;
        }
        for (int i = 0; i < schoolBooks.length; i++) {

            if (schoolBooks[i].getName().equals(name)) {
                findSchoolBooks[count] = schoolBooks[i];
                count++;
            }

        }
        SchoolBook[] finishFind = new SchoolBook[count];

        for (int i = 0; i < findSchoolBooks.length; i++) {
            if (findSchoolBooks[i] != null) {
                finishFind[i] = findSchoolBooks[i];
            }
        }
        return finishFind;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length != 0) {
            int count = 0;
            SchoolBook[] removeBook = Arrays.copyOf(schoolBooks, schoolBooks.length);
            for (int i = 0; i < removeBook.length; i++) {
                if (removeBook[i].getName().equals(name)) {
                    removeBook[i] = null;
                }
            }
            SchoolBook[] finishRemoveBook = new SchoolBook[schoolBooks.length - findByName(name).length];
            for (int i = 0; i < removeBook.length; i++) {
                if (removeBook[i] != null) {

                    finishRemoveBook[count] = removeBook[i];
                    count++;
                }
            }
            schoolBooks = finishRemoveBook;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}