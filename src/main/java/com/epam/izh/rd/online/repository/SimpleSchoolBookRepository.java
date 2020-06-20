package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    public SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks).filter(x -> x.getName() == name).toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        int temp = schoolBooks.length;
        schoolBooks = Arrays.stream(schoolBooks).filter(x -> !(x.getName() == name)).toArray(SchoolBook[]::new);
        return schoolBooks.length < temp;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
