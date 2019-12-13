package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooksTemp = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, schoolBooksTemp, 0, schoolBooks.length);
        schoolBooksTemp[schoolBooksTemp.length - 1] = book;
        schoolBooks = schoolBooksTemp;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int bookNameSearch = 0;
        if (schoolBooks == null) {
            return new SchoolBook[bookNameSearch];
        } else {
            for (SchoolBook bookName : schoolBooks) {
                if (bookName.getName().equalsIgnoreCase(name)) {
                    bookNameSearch++;
                }
            }
            SchoolBook[] schoolBooksResult = new SchoolBook[bookNameSearch];
            for (int i = 0, j = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equalsIgnoreCase(name)) {
                    schoolBooksResult[j] = schoolBooks[i];
                    j++;
                }
            }
            return schoolBooksResult;
        }

    }

    @Override
    public boolean removeByName(String name) {
        if (schoolBooks == null) {
            return false;
        }
        int bookNameSearch = schoolBooks.length;
        for (SchoolBook bookName : schoolBooks) {
            if (bookName.getName().equalsIgnoreCase(name)) {
                bookNameSearch--;
            }
        }
        SchoolBook[] schoolBooksResult = new SchoolBook[bookNameSearch];
        for (int i = 0, j = 0; i < schoolBooks.length; i++) {
            if (!schoolBooks[i].getName().equalsIgnoreCase(name)) {
                schoolBooksResult[j] = schoolBooks[i];
                j++;
            }
        }
        schoolBooks = schoolBooksResult;
        return true;
    }

    @Override
    public int count() {
        if (schoolBooks == null) {
            return 0;
        } else {
            return schoolBooks.length;
        }
    }
}
