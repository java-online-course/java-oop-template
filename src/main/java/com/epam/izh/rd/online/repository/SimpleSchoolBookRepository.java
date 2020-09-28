package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};


    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] temp = new SchoolBook[schoolBooks.length+1];
        for (int i = 0; i < schoolBooks.length; i++) {
            temp[i] = schoolBooks[i];
        }
        temp[schoolBooks.length] = book;
        schoolBooks = temp;
        return true;
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
            }
            return Arrays.copyOf(findBook, i);
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
            if (count > 1) {
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - (count - 1));
                return true;
            } else {
                return false;
            }
        } else return false;
    }


    @Override
    public int count() {
        return schoolBooks.length;
    }
}
