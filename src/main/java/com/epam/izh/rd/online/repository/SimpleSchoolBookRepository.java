package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    public boolean save(SchoolBook book) {
        this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;

    }

    public SchoolBook[] findByName(String name) {

        SchoolBook[] findBooks = new SchoolBook[0];

        for (int i = 0; i < schoolBooks.length; i++) {

            if (schoolBooks[i].getName().equals(name)) {

                findBooks = Arrays.copyOf(findBooks, findBooks.length + 1);
                findBooks[findBooks.length - 1] = schoolBooks[i];
            }
        }
        return findBooks;

    }

    public boolean removeByName(String name) {

        boolean x = false;

        for (int i = 0; i < schoolBooks.length; i++) {

            if (schoolBooks[i].getName().equals(name)) {

                SchoolBook copy = schoolBooks[i];
                schoolBooks[i] = schoolBooks[schoolBooks.length - 1];
                schoolBooks[schoolBooks.length - 1] = copy;

                this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);

                if ((schoolBooks.length == 1) && schoolBooks[0].getName().equals(copy.getName())) {

                    this.schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                }
                x = true;
            }
        }
        return x;
    }

    public int count() {
        return schoolBooks.length;
    }

}
