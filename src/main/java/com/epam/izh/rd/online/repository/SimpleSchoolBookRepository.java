package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tempBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, tempBooks, 0, schoolBooks.length);
        tempBooks[tempBooks.length - 1] = book;
        schoolBooks = new SchoolBook[tempBooks.length];
        System.arraycopy(tempBooks, 0, schoolBooks, 0, tempBooks.length);
        for (SchoolBook sb : schoolBooks) {
            if (sb.equals(book)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int k = 0;
        SchoolBook[] temp = new SchoolBook[schoolBooks.length];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                temp[k] = schoolBooks[i];
                k++;
            }
        }
        return Arrays.copyOf(temp, k);
    }

    @Override
    public boolean removeByName(String name) {
        int searchedElement = 0;
        if (findByName(name) == null) {
            return false;
        } else {
            for (int i = schoolBooks.length - 1; i >= 0; i--) {
                if (schoolBooks[i].getName().equals(name)) {
                    searchedElement = i;
                    SchoolBook[] authorsTemp = new SchoolBook[schoolBooks.length - 1];
                    System.arraycopy(schoolBooks, 0, authorsTemp, 0, searchedElement);
                    System.arraycopy(schoolBooks, searchedElement + 1, authorsTemp, searchedElement, schoolBooks.length - searchedElement - 1);
                    schoolBooks = new SchoolBook[authorsTemp.length];
                    System.arraycopy(authorsTemp, 0, schoolBooks, 0, authorsTemp.length);
                }
            }
            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}