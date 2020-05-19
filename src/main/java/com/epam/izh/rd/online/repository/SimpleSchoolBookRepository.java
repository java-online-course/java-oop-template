package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks;

    @Override
    public boolean save(SchoolBook book) {
        if (schoolBooks != null) {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
            schoolBooks[schoolBooks.length - 1] = book;
            return true;
        } else {
            schoolBooks = new SchoolBook[1];
            schoolBooks[0] = book;
            return true;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (schoolBooks != null) {
            SchoolBook[] findBook = new SchoolBook[schoolBooks.length];
            int i = 0;
            for (SchoolBook schoolBook : schoolBooks
            ) {
                if (schoolBook.getName().equals(name)) {
                   findBook[i] = schoolBook;
                   i++;
                }
            } return Arrays.copyOf(findBook, i);
        } else return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        if (schoolBooks != null) {
            int count = 1;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    schoolBooks[i] = schoolBooks[schoolBooks.length - count];
                    count++;
                }
            }
            if (count>1) {
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - (count - 1));
                return true;
            }else {
                return false;
            }
        } else return false;
    }

    @Override
    public int count() {
        if (schoolBooks != null){
        return schoolBooks.length;
        }else return 0;
    }
}
