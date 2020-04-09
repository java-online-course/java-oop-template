package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[0];
    @Override
    public boolean save(SchoolBook book) {
        int pastCount = schoolBooks.length;
        schoolBooks = ArrayUtils.addAll(schoolBooks, book);
        int presentCount = schoolBooks.length;
        return pastCount != presentCount;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findBook = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                findBook = ArrayUtils.addAll(findBook, schoolBook);
            }
        }
        return findBook;
    }

    @Override
    public boolean removeByName(String name) {
        boolean remove = false;
        int count = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                count++;
                remove = true;
                schoolBooks[i] = null;
            }
        }
        SchoolBook[] delBook = new SchoolBook[schoolBooks.length - count];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook != null) {
                delBook = ArrayUtils.addAll(delBook, schoolBook);
            }
        }
        schoolBooks = delBook;
        return remove;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
