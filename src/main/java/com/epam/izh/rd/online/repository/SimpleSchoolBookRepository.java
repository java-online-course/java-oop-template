
package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newArraySchoolBooks = new SchoolBook[count()+1];
        System.arraycopy(schoolBooks, 0, newArraySchoolBooks, 0, count());
        newArraySchoolBooks[count()] = (SchoolBook) book;
        schoolBooks = newArraySchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
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
        // Book not found
        if (findByName(name).length == 0) {
            return false;
        }
        SchoolBook[] newSchoolBooks = new SchoolBook[count() - findByName(name) .length];
        int index = 0;
        for (SchoolBook book : schoolBooks) {
            boolean isDelete = false;
            for (Book removedBook : findByName(name) ) {
                if (book.equals(removedBook)) {
                    isDelete = true;
                    break;
                }
            }
            if (!isDelete) {
                newSchoolBooks[index] = book;
                index++;
            }
        }
        schoolBooks = newSchoolBooks;
        return true;
    }
    @Override
    public int count() {
        return schoolBooks.length;
    }
}
