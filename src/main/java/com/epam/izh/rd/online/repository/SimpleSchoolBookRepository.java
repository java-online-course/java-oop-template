package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private static int count = 0;
    private SchoolBook[] schoolBooks = new SchoolBook[count];


    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooks1 = new SchoolBook[count];
        schoolBooks1 = Arrays.copyOf(schoolBooks, count);
        count++;
        schoolBooks = Arrays.copyOf(schoolBooks1, count);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }


    @Override
    public SchoolBook[] findByName(String name) {
        int index = 0;
        SchoolBook[] foundBook = new SchoolBook[0];
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                index++;
            }
        }
        foundBook = new SchoolBook[index];
        int foundBookIndex = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                foundBook[foundBookIndex] = schoolBook;
                foundBookIndex++;
            }
        }
        return foundBook;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] schoolBooks1 = new SchoolBook[0];
        schoolBooks1 = Arrays.copyOf(schoolBooks, count);
        if (findByName(name).length != 0) {
            for (int i = 0; i < schoolBooks1.length; i++) {
                if (schoolBooks1[i].getName().equals(name)) {
                    SchoolBook schoolBook = new SchoolBook();
                    schoolBook = schoolBooks1[i];
                    count--;
                    if (schoolBooks1[i].equals(schoolBook)) {
                        schoolBooks1[i] = schoolBooks1[schoolBooks1.length - 1];
                        schoolBooks1[schoolBooks1.length - 1] = schoolBook;
                    }
                }
            }
            schoolBooks = Arrays.copyOf(schoolBooks1, count);
            return true;
        }

        return false;
    }


    @Override
    public int count() {
        return count;
    }
}
