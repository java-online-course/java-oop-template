package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] increasedSchoolBooks = Arrays.copyOf(this.schoolBooks,
                this.count() + 1);
        increasedSchoolBooks[this.count()] = book;
        this.schoolBooks = increasedSchoolBooks;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundedSchoolBooks = new SchoolBook[0];

        for (int i = 0; i < this.count(); i++) {
            if (this.schoolBooks[i].getName().equals(name)) {
                foundedSchoolBooks = Arrays.copyOf(foundedSchoolBooks,
                        foundedSchoolBooks.length + 1);
                foundedSchoolBooks[i] = this.schoolBooks[i];
            }
        }
        return foundedSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (this.findByName(name).length == 0) {
            return false;
        }
        SchoolBook[] decreasedSchoolBooks = new SchoolBook[this.count()];
        int posToInsertExistingBook = 0;

        for (int i = 0; i < this.count(); i++) {
            if (this.schoolBooks[i].getName().equals(name)) {
                decreasedSchoolBooks = Arrays.copyOf(decreasedSchoolBooks,
                        decreasedSchoolBooks.length - 1);
                posToInsertExistingBook--;
            } else {
                decreasedSchoolBooks[posToInsertExistingBook] = this.schoolBooks[i];
                posToInsertExistingBook++;
            }
        }
        this.schoolBooks = decreasedSchoolBooks;

        return true;
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}
