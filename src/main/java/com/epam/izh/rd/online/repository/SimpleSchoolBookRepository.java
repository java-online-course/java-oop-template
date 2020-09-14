package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
     if (Objects.isNull(book)) {
         return false;
     }
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks)
                .filter(e->e.getName().equalsIgnoreCase(name))
                .toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] foundedBooksOrEmptyArray = findByName(name);
        if (foundedBooksOrEmptyArray.length == 0) {
            return false;
        }

        for (SchoolBook book : foundedBooksOrEmptyArray) {
            for (int j = 0; j < schoolBooks.length; j++) {
                if (book.equals(schoolBooks[j])) {
                    schoolBooks[j] = null;
                }
            }
        }

        schoolBooks = Arrays.stream(schoolBooks)
                .filter(Objects::nonNull)
                .toArray(SchoolBook[]::new);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
