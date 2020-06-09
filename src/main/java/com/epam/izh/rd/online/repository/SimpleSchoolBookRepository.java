package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.service.AuthorService;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] res = new SchoolBook[count() + 1];
        for (int i = 0; i < count(); i++) {
            res[i] = schoolBooks[i];
        }
        res[count()] = book;
        schoolBooks = res;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int size = 0;
        int j = 0;

        for (SchoolBook book : schoolBooks) {
            if (book.getName() == name) {
                size++;
            }
        }

        SchoolBook[] res = new SchoolBook[size];
        for (SchoolBook book : schoolBooks) {
            if (book.getName() == name) {
                res[j++] = book;
            }
        }

        return res;
    }

    @Override
    public boolean removeByName(String name) {

        int size = schoolBooks.length;

        if (size == 0) {
            return false;
        }
        int j = 0;

        for (SchoolBook book : schoolBooks) {
            if (book.getName() == name) {
                size--;
            }
        }

        if (size == schoolBooks.length) {
            return false;
        } else {

            SchoolBook[] res = new SchoolBook[size];
            for (SchoolBook book : schoolBooks) {
                if (book.getName() != name) {
                    res[j++] = book;
                }
            }

            schoolBooks = res;

            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}