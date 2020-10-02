package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks,schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] demo = new SchoolBook[0];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().toUpperCase().equals(name.toUpperCase())){
                demo = Arrays.copyOf(demo,demo.length + 1);
                demo[demo.length-1] = schoolBooks[i];
            }
        }
        return demo;
    }

    @Override
    public boolean removeByName(String name) {
        int count = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().toUpperCase().equals(name.toUpperCase())) count++;
        }
        if (count == 0) return false;
        SchoolBook[] demo = new SchoolBook[0];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (!schoolBooks[i].getName().toUpperCase().equals(name.toUpperCase())){
                demo = Arrays.copyOf(demo,demo.length + 1);
                demo[demo.length-1] = schoolBooks[i];
            }
        }
        schoolBooks = Arrays.copyOf(demo,demo.length);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
