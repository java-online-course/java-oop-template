package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] resultArr = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        resultArr[resultArr.length - 1] = book;
        schoolBooks = resultArr;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks).filter(theSchoolBook ->
                theSchoolBook.getAuthorName().equals(name))
                .toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }

        SchoolBook[] tempArr = Arrays.stream(schoolBooks)
                .filter(book -> !book.getAuthorName().equals(name))
                .toArray(SchoolBook[]::new);
        schoolBooks = tempArr;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
