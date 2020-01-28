package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;


public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, count());
        newSchoolBooks[count()] = (SchoolBook) book;
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public boolean save(Book book) {
        return false;
    }

    @Override
    public Book findByName(String name) {
        // Initialization
        int amountBook = 0;
        SchoolBook[] arrayBooksFindByName;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName() == name) {
                amountBook++;
            }
        }
        // Return empty array
        if (amountBook == 0) {
            return new SchoolBook[]{};
        }

        arrayBooksFindByName = new SchoolBook[amountBook];
        amountBook = 0;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName() == name) {
                arrayBooksFindByName[amountBook] = schoolBook;
                amountBook++;
            }
        }
        return arrayBooksFindByName;
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
