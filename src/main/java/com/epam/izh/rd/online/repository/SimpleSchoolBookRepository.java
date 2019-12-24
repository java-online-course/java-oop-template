package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        newSchoolBooks[schoolBooks.length] = book;
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int j = 0;
        SchoolBook[] result = new SchoolBook[0];
        for (SchoolBook sb : this.schoolBooks) {
            if (sb.getName().equals(name)) {
                result = Arrays.copyOf(result, j + 1);
                result[j] = sb;
                j++;
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (name.equals(schoolBook.getName())) {
                count++;
            }
        }
        if (count > 0) {
            int j = 0;
            SchoolBook[] newSchoolBook = new SchoolBook[schoolBooks.length - count];
            for (SchoolBook schoolBook : schoolBooks) {
                if (!name.equals(schoolBook.getName())) {
                    newSchoolBook[j] = schoolBook;
                    j++;
                }
            }
            schoolBooks = newSchoolBook;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
