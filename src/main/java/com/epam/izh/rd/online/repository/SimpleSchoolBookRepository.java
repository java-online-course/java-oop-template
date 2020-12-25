package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] fieldForCopy = new SchoolBook[count()];
        System.arraycopy(schoolBooks, 0, fieldForCopy, 0, schoolBooks.length);
        schoolBooks = new SchoolBook[count() + 1];
        System.arraycopy(fieldForCopy, 0, schoolBooks, 0, fieldForCopy.length);
        schoolBooks[count() - 1] = book;
        return true;

    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] schoolBooks = new SchoolBook[0];
        for (int i = 0; i < this.schoolBooks.length; i++) {
            if (this.schoolBooks[i].getName() == name) {
                SchoolBook[] fieldForCopy = new SchoolBook[schoolBooks.length + 1];
                System.arraycopy(schoolBooks, 0, fieldForCopy, 0, schoolBooks.length);
                schoolBooks = new SchoolBook[fieldForCopy.length];
                System.arraycopy(fieldForCopy, 0, schoolBooks, 0, schoolBooks.length);

                schoolBooks[schoolBooks.length - 1] = this.schoolBooks[i];
            }
        }
        return schoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        SchoolBook[] fieldForCopy = new SchoolBook[count() - findByName(name).length];
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName() != name) {
                fieldForCopy[i] = schoolBooks[i];

            }
        }
        schoolBooks = fieldForCopy;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
