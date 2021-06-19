package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {


            SchoolBook[]temp = new SchoolBook[schoolBooks.length];
            System.arraycopy(schoolBooks, 0, temp, 0, schoolBooks.length);
            schoolBooks = new SchoolBook[schoolBooks.length+1];
            System.arraycopy(temp, 0, schoolBooks, 0, temp.length);
            schoolBooks[schoolBooks.length-1] = book;


            return true;
 }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] schoolBooksLocal = {};

        for(SchoolBook schoolBook : schoolBooks){
            if(schoolBook.getName().equals(name)){
                SchoolBook[] temp = new SchoolBook[schoolBooksLocal.length];
                System.arraycopy(schoolBooksLocal, 0, temp, 0, schoolBooksLocal.length);
                schoolBooksLocal = new SchoolBook[schoolBooksLocal.length+1];
                System.arraycopy(temp, 0, schoolBooksLocal, 0, temp.length);
                schoolBooksLocal[schoolBooksLocal.length-1]=schoolBook;
            }
        }
        return schoolBooksLocal;
    }

    @Override
    public boolean removeByName(String name) {
      boolean exist = false;
        int count = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if(name.equals(schoolBooks[i].getName())){
                exist = true;
                count++;
                schoolBooks[i] = schoolBooks[schoolBooks.length-count];
            }
        }

        if(exist){
            SchoolBook[] temp = new SchoolBook[schoolBooks.length-count];
            System.arraycopy(schoolBooks,0,temp,0,temp.length);
            schoolBooks = new SchoolBook[temp.length];
            System.arraycopy(temp,0,schoolBooks,0,temp.length);

        }

        return exist;

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
