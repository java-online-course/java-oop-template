package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements  BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] bufferBooks = new SchoolBook[schoolBooks.length + 1];
        for(int i = 0; i < schoolBooks.length; i++){
            bufferBooks[i] = schoolBooks[i];
        }
        bufferBooks[bufferBooks.length - 1] = book;
        schoolBooks = bufferBooks;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
