package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    public SimpleSchoolBookRepository() {
    }

    public SimpleSchoolBookRepository(SchoolBook[] schoolBooks) {
        this.schoolBooks = schoolBooks;
    }

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] nameBook = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                nameBook = Arrays.copyOf(nameBook, nameBook.length + 1);
                nameBook[nameBook.length - 1] = schoolBook;
            }
        }
        return nameBook;
    }

    @Override
    public boolean removeByName(String name) {
        boolean deleteSuccess = false;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;

                System.arraycopy(schoolBooks, 0, schoolBooks, 0, i);
                System.arraycopy(schoolBooks, i + 1, schoolBooks, i, schoolBooks.length - 1 - i);
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);

                deleteSuccess = true;
                i--;
            }
        }
        return deleteSuccess;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
