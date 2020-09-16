package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] booksBuffer = new SchoolBook[schoolBooks.length+1];
        for(int i = 0; i < schoolBooks.length; i++) {
            booksBuffer [i] = schoolBooks [i];
        }
        booksBuffer[schoolBooks.length] = book;
        schoolBooks = booksBuffer;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundBooks = new SchoolBook[]{};
        for(int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i].getName().equals(name)) {
                foundBooks = Arrays.copyOf(foundBooks, foundBooks.length + 1);
                foundBooks[foundBooks.length - 1] = schoolBooks[i];
            }
        }
        return foundBooks;
    }

    @Override
    public boolean removeByName(String name){
        if(findByName(name).length == 0) {
            return false;
        } else {
            SchoolBook[] booksBuffer = new SchoolBook[schoolBooks.length - findByName(name).length];
            int i = 0;
            for(SchoolBook booksFound : schoolBooks) {
                if(booksFound.getName() != name) {
                    booksBuffer[i] = booksFound;
                    i++;
                }
            }
            schoolBooks = booksBuffer;
            return true;
        }
    }

    @Override
    public int count(){
        return schoolBooks.length;
    }
}
