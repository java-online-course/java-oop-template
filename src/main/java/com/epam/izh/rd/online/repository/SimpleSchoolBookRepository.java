package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook schoolBook) {
        SchoolBook[] schoolBooksUpdated = new SchoolBook[count() + 1];
        for (int i = 0; i < count(); i++) {
            schoolBooksUpdated[i] = schoolBooks[i];
        }
        schoolBooksUpdated[count()] = schoolBook;
        schoolBooks = schoolBooksUpdated;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int bookCounter = 0;
        SchoolBook[] bookAccumulator;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName() == name) {
                bookCounter++;
            }
        }

        if (bookCounter == 0) {
            return new SchoolBook[]{};
        }

        bookAccumulator = new SchoolBook[bookCounter];
        bookCounter = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName() == name) {
                bookAccumulator[bookCounter] = schoolBook;
                bookCounter++;
            }
        }

        return bookAccumulator;
    }

    @Override
    public boolean removeByName(String name) {
        int schoolBookCounter = findByName(name).length;
        if (schoolBookCounter == 0) {
            return false;
        }

        SchoolBook[] schoolBooksUpdated = new SchoolBook[count() - schoolBookCounter];
        schoolBookCounter = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName() != name) {
                schoolBooksUpdated[schoolBookCounter] = schoolBook;
                schoolBookCounter++;
            }
        }
        schoolBooks = schoolBooksUpdated;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
