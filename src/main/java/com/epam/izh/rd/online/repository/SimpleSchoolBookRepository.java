package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks =  {};

    public SimpleSchoolBookRepository() {

    }

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] testArr = new SchoolBook[count() + 1];
        int j = 0;
        for (int i = 0; i < count(); i++) {
            testArr[i] = schoolBooks[i];
        }
        testArr[count()] = book;
        schoolBooks = null;
        schoolBooks = testArr;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int size = 0;
        int j = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName() == name){
                size++;
            }
        }
        SchoolBook[] testArr = new SchoolBook[size];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName() == name) {
                testArr[j++] = schoolBooks[i];
            }
        }
            return testArr;
    }
    @Override
    public boolean removeByName(String name) {
        boolean[] booleansArr = new boolean[count()];
        int size = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName() == name)
            {
                size++;
                booleansArr[i] = true;
            }
        }
        if (size !=0 ){
            SchoolBook[] testArr = schoolBooks;
            schoolBooks = new SchoolBook[schoolBooks.length - size];
            int j = 0;
        for (int i = 0; i < testArr.length; i++) {
            if (!booleansArr[i]){
                schoolBooks[j++] = testArr[i]; }
        }
        return true;
        }
        else {
            return false;}
    }
    @Override
    public int count() {
       return schoolBooks.length;
    }
}
