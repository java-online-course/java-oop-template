package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks,schoolBooks.length + 1);
        schoolBooks[schoolBooks.length-1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundBooks = new SchoolBook[0];
        for (SchoolBook book : schoolBooks) {
            if(book.getName().equals(name)) {
                foundBooks = Arrays.copyOf(foundBooks, foundBooks.length+1);
                foundBooks[foundBooks.length-1] = book;
            }
        }
        return foundBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int removedBooks = 0;
        for(int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;
                removedBooks++;
            }
        }
        if(removedBooks == 0) {
            return false;
        }

        SchoolBook[] nonNullArray = new SchoolBook[schoolBooks.length-removedBooks];
        int nonNullNextIndex = 0;
        for(int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i] != null) {
                nonNullArray[nonNullNextIndex] = schoolBooks[i];
                nonNullNextIndex++;
            }
        }
        schoolBooks = nonNullArray;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
