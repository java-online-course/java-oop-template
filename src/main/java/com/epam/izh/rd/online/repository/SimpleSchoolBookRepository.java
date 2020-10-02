package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];


    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().toUpperCase().equals(name.toUpperCase())) {
                count++;
            }
        }
        if (count > 0) {
            SchoolBook[] newSchoolBooks = new SchoolBook[count];
            int index = 0;
            for (SchoolBook schoolBook : schoolBooks) {
                if (schoolBook.getName().toUpperCase().equals(name.toUpperCase())) {
                    newSchoolBooks[index++] = schoolBook;
                }
            }
            return newSchoolBooks;
        } else return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int count = 0;
        for (int i = 0; i < schoolBooks.length - count; i++) {
            if (schoolBooks[i].getName().toUpperCase().equals(name.toUpperCase())) {
                count++;
                for (int j = i; j < schoolBooks.length - 1; j++) {
                    SchoolBook temp = schoolBooks[j];
                    schoolBooks[j] = schoolBooks[j + 1];
                    schoolBooks[j + 1] = temp;
                }
                i -= 1;
            }
        }
        if (count > 0) {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - count);
            return true;
        } else return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
