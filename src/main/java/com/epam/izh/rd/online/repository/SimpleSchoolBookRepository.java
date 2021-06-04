package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        int lengthArray = count();
        schoolBooks = Arrays.copyOf(schoolBooks, lengthArray + 1);
        schoolBooks[lengthArray] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int lengthArray = count();
        int quantityBooks = 0;
        int numberOfIndex = 0;
        for (int i = 0; i < lengthArray; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                quantityBooks++;
            }
        }
        if (quantityBooks == 0) {
            return new SchoolBook[0];
        }
        SchoolBook[] schoolBooksFindByName = new SchoolBook[quantityBooks];
        for (int i = 0; i < lengthArray; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooksFindByName[numberOfIndex] = schoolBooks[i];
                numberOfIndex++;
            }
        }
        return schoolBooksFindByName;
    }

    @Override
    public boolean removeByName(String name) {
        if (count() != 0) {
            int numberOfNotRemoved = count() - findByName(name).length;
            SchoolBook[] tempArray = new SchoolBook[numberOfNotRemoved];
            int numberOfIndex = 0;
            for (int i = 0; i < count(); i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    tempArray[numberOfIndex] = schoolBooks[i];
                    numberOfIndex++;
                }
            }
            schoolBooks = Arrays.copyOf(tempArray, numberOfNotRemoved);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
