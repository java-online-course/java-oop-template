package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import static java.util.Arrays.copyOf;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] schoolBooksNew = copyOf(this.schoolBooks, this.schoolBooks.length + 1);
        schoolBooksNew[schoolBooksNew.length - 1] = book;
        this.schoolBooks = schoolBooksNew;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int numberOfBooksFound = 0;

        for (SchoolBook book: this.schoolBooks) {
            if (book.getName() == name) {
                numberOfBooksFound++;
            }
        }

        if (numberOfBooksFound == 0) {
            return new SchoolBook[0];
        }

        SchoolBook[] booksFound = new SchoolBook[numberOfBooksFound];

        int shiftOfArrayElements = 0;
        for (int i = 0; i < this.schoolBooks.length; i++) {
            if (this.schoolBooks[i].getName() == name) {
                booksFound[i - shiftOfArrayElements] = this.schoolBooks[i];
            } else {
                shiftOfArrayElements++;
            }
        }

        return booksFound;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name) == null) {
            return false;
        }

        int numberOfFoundBooks = 0;
        for (SchoolBook book: this.schoolBooks) {
            if (book.getName() == name) {
                numberOfFoundBooks++;
            }
        }

        SchoolBook[] schoolBooksNew = new SchoolBook[this.schoolBooks.length - numberOfFoundBooks];

        int shiftOfArrayElements = 0;
        for (int i = 0; i < this.schoolBooks.length; i++) {
            if (this.schoolBooks[i].getName() != name) {
                schoolBooksNew[i - shiftOfArrayElements] = this.schoolBooks[i];
            } else {
                shiftOfArrayElements++;
            }
        }

        this.schoolBooks = schoolBooksNew;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
