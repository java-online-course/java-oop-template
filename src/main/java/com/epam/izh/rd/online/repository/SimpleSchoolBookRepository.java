package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = new SchoolBook[] {};

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Stream.concat(Arrays.stream(schoolBooks), Stream.of(book)).toArray(SchoolBook[]::new);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks).filter(book -> book.getName().equals(name)).toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        int countBefore = count();
        schoolBooks = Arrays.stream(schoolBooks).filter(book -> !book.getName().equals(name)).toArray(SchoolBook[]::new);
        return countBefore != count();
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
