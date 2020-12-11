package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks;
    {
        schoolBooks = new SchoolBook[]{};
    }

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] listSchoolBooks = new SchoolBook[0];
        for (SchoolBook itemSchoolBooks : schoolBooks) {
            if (itemSchoolBooks.getName().equals(name)) {
                listSchoolBooks = Arrays.copyOf(listSchoolBooks, listSchoolBooks.length + 1);
                listSchoolBooks[listSchoolBooks.length - 1] = itemSchoolBooks;
            }
        }
        return listSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int countRemoveByName = this.findByName(name).length;
        if (countRemoveByName > 0) {
            SchoolBook[] copySchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length);
            schoolBooks = new SchoolBook[schoolBooks.length - countRemoveByName];
            int count = 0;
            for (SchoolBook itemSchoolBooks : copySchoolBooks) {
                if (!itemSchoolBooks.getName().equals(name)) {
                    schoolBooks[count] = itemSchoolBooks;
                    count++;
                }
            }
            return true;
        } else {
        return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
