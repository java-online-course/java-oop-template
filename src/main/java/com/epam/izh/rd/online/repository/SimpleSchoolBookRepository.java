package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.ArrayList;

public class SimpleSchoolBookRepository implements BookRepository{
    SchoolBook[] schoolbooks = new SchoolBook[]{};

    @Override
    public boolean save(Book book) {
        SchoolBook[] booksBuffer = new SchoolBook[schoolbooks.length+1];
        for (int i = 0; i < schoolbooks.length; i++) {
            booksBuffer[i] = schoolbooks[i];
        }
        booksBuffer[schoolbooks.length] = (SchoolBook) book;
        schoolbooks = booksBuffer;
        return true;
    }

    @Override
    public Book[] findByName(String name) {
        ArrayList<SchoolBook> bookBuffer = new ArrayList<>();
        for (int i = 0; i < schoolbooks.length; i++)
            if(schoolbooks[i].getName().equals(name)){
                bookBuffer.add(schoolbooks[i]);
            }
        SchoolBook[] books = new SchoolBook[bookBuffer.size()];
        for (int i = 0; i < bookBuffer.size(); i++)
            books[i]=bookBuffer.get(i);
        return books;

    }

    @Override
    public boolean removeByName(String name) {
        if(findByName(name).length==0) return false;
        SchoolBook[] booksBuffer = new SchoolBook[count() - findByName(name).length];
        int count = 0;
        for (SchoolBook schoolBook : schoolbooks) {
            if (!schoolBook.getName().equals(name)) {
                booksBuffer[count] = schoolBook;
                count++;
            }
        }
        schoolbooks = booksBuffer;
        return true;
    }

    @Override
    public int count() {
        return schoolbooks.length;
    }
}
