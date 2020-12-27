package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[]{};


    @Override
    public boolean save(SchoolBook book) {
        if (book != null){
            SchoolBook[] schoolBooks = new SchoolBook[this.schoolBooks.length + 1];
            for (int i = 0; i < this.schoolBooks.length ; i++) {
                schoolBooks[i] = this.schoolBooks[i];
            }
            schoolBooks[this.schoolBooks.length] = book;
            this.schoolBooks = schoolBooks;
            return true;
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int countOfFoundSchoolBooks = 0;
        for (SchoolBook schoolBook: schoolBooks
        ) {
            if (schoolBook.getName().equals(name)){
                countOfFoundSchoolBooks++;
            }
        }

        SchoolBook[] foundSchoolBooks = new SchoolBook[countOfFoundSchoolBooks];

        int j = 0;
        for (SchoolBook schoolBook: schoolBooks
        ) {
            if (schoolBook.getName().equals(name)){
                foundSchoolBooks[j++] = schoolBook;
            }
        }

        return foundSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int amountOfFoundBooks = this.findByName(name).length;
        if( amountOfFoundBooks != 0){
            SchoolBook[] schoolBooks = new SchoolBook[this.count() - amountOfFoundBooks];
            int i = 0;
            for (SchoolBook schoolBook: this.schoolBooks
            ) {
                if (!schoolBook.getName().equals(name)){
                    schoolBooks[i++] = schoolBook;
                }
            }
            this.schoolBooks = schoolBooks;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}