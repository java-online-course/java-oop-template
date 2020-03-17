package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.ArrayList;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[]{};
    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] booksBuffer = new SchoolBook[schoolBooks.length+1];
        for (int i = 0; i < schoolBooks.length; i++) {
            booksBuffer[i] = schoolBooks[i];
        }
        booksBuffer[schoolBooks.length] = book;
        schoolBooks = booksBuffer;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        ArrayList<SchoolBook> bookBuffer = new ArrayList<>();
        for (int i = 0; i < schoolBooks.length; i++)
            if(schoolBooks[i].getName().equals(name)){
                bookBuffer.add(schoolBooks[i]);
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
        for (SchoolBook schoolBook : schoolBooks) {
            if (!schoolBook.getName().equals(name)) {
                booksBuffer[count] = schoolBook;
                count++;
            }
        }
        schoolBooks = booksBuffer;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
