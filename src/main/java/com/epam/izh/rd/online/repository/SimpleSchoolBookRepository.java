package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tmp = schoolBooks.clone();
        schoolBooks = new SchoolBook[tmp.length + 1];
        for (int i = 0; i < tmp.length; i++) {
            schoolBooks[i] = tmp[i];
        }
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int lengthForResArr = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                lengthForResArr++;
            }
        }
        SchoolBook[] result = new SchoolBook[lengthForResArr];
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                result[count] = schoolBook;
                System.out.println(result[count]);
                count++;
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        int findByNameLength = findByName(name).length;
        if (findByNameLength > 0) {
            SchoolBook[] resutl = new SchoolBook[schoolBooks.length - findByNameLength];
            int count = 0;
            for (SchoolBook schoolBook : schoolBooks) {
                if (!(schoolBook.getName().equals(name))) {
                    resutl[count] = schoolBook;
                    count++;
                }
            }
            schoolBooks = resutl;
            return true;
        } else return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
