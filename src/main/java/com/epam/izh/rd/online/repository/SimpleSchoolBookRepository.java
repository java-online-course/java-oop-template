package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.ArrayList;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tempBooks = new SchoolBook[schoolBooks.length + 1];
        int position = 0;
        for (SchoolBook book1 : schoolBooks) {
            tempBooks[position] = book1;
            position++;
        }
        tempBooks[position] = book;
        schoolBooks = tempBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        ArrayList<SchoolBook> tempBooks = new ArrayList();
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                tempBooks.add(schoolBook);
            }
        }
        return tempBooks.toArray(new SchoolBook[tempBooks.size()]);
    }

    @Override
    public boolean removeByName(String name) {
        boolean isRemoved = false;
        ArrayList<SchoolBook> tempBooks = new ArrayList();
        for (SchoolBook schoolBook : schoolBooks) {
            if (!schoolBook.getName().equals(name)) {
                tempBooks.add(schoolBook);
            }
        }
        if (tempBooks.size() != schoolBooks.length) {
            schoolBooks = tempBooks.toArray(new SchoolBook[tempBooks.size()]);
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
