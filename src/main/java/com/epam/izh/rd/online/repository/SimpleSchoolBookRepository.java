package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];


    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] copy = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, copy, 0, schoolBooks.length);
        schoolBooks = copy;
        schoolBooks[schoolBooks.length - 1] = book;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int countBook = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName() == name) {
                countBook++;
            }
        }
        if(countBook>0){
            SchoolBook[] second = new SchoolBook[countBook];
            int j=0;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName() == name) {
                    second[j++] = schoolBooks[i];
                }
            }
            return second;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int countBook = 0;
        for (int i = 0; i<schoolBooks.length; i++){
            if (schoolBooks[i].getName() == name){
                countBook++;
            }
        }
        if (countBook>0){
            SchoolBook[] removeBooks = new SchoolBook[schoolBooks.length - countBook];
            int index = 0;
            for (int i = 0; i<schoolBooks.length; i++){
                if (schoolBooks[i].getName() != name){
                    removeBooks[index++] = schoolBooks[i];

                }
            }
            schoolBooks = removeBooks;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
