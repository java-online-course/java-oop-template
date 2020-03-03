package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.LinkedList;
import java.util.List;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    public SimpleSchoolBookRepository() {
    }

    @Override
    public boolean save(SchoolBook book) {

        if (this.schoolBooks.length == 0) {
            this.schoolBooks = new SchoolBook[]{book};
            return true;
        }

        SchoolBook[] result = new SchoolBook[this.schoolBooks.length + 1];
        System.arraycopy(this.schoolBooks, 0, result, 0, this.schoolBooks.length);
        result[this.schoolBooks.length] = book;
        this.schoolBooks = result;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {

        List<SchoolBook> schoolBooksSearch = new LinkedList<>();
        for (SchoolBook book : this.schoolBooks) {
            if (book.getName().equals(name)) {
                schoolBooksSearch.add(book);
            }
        }

        return schoolBooksSearch.size() > 0 ? (SchoolBook[]) schoolBooksSearch.toArray(new SchoolBook[]{}) : new SchoolBook[]{};
    }

    @Override
    public boolean removeByName(String name) {

        List<SchoolBook> schoolBooksSearch = new LinkedList<>();
        for (SchoolBook book : this.schoolBooks) {
            if (!(book.getName().equals(name))) {
                schoolBooksSearch.add(book);
            }
        }

        if (schoolBooksSearch.size() != this.schoolBooks.length) {
            this.schoolBooks = (SchoolBook[]) schoolBooksSearch.toArray(new SchoolBook[]{});
            return true;
        }

        return false;
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}