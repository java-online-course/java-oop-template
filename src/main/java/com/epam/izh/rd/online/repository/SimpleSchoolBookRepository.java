package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};


    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] temp = new SchoolBook[schoolBooks.length+1];
        for (int i = 0; i < schoolBooks.length; i++) {
            schoolBooks[i] = temp[i];
        }
        temp[schoolBooks.length] = book;
        schoolBooks = temp;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int j=0;
        SchoolBook[] findRez = new SchoolBook[schoolBooks.length+1];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)){
                findRez[++j] = schoolBooks[i];
            }
            schoolBooks = findRez;
            return schoolBooks;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int j=0;

        SchoolBook[] temp = new SchoolBook[schoolBooks.length+1];
        for (int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i].getName().equals(name)){

            }else {
                temp[++j] = schoolBooks[i];
            }
            schoolBooks = temp;
            return true;
        }
        return false;
    }


    @Override
    public int count() {

        return schoolBooks.length;
    }
}
