package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements  BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] bufferBooks = new SchoolBook[schoolBooks.length + 1];
        for(int i = 0; i < schoolBooks.length; i++){
            bufferBooks[i] = schoolBooks[i];
        }
        bufferBooks[bufferBooks.length - 1] = book;
        schoolBooks = bufferBooks;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {

        int count = 0;
        Integer[] foundIndices = new Integer[schoolBooks.length];
        for(int i = 0; i < schoolBooks.length; i++){
            if(schoolBooks[i].getName().equals(name)){
                foundIndices[count] = i;
                count ++;
            }
        }

        SchoolBook[] foundBooks = new SchoolBook[count];
        for(int i = 0; i < count; i++){
            foundBooks[i] = schoolBooks[foundIndices[i]];
        }

        return foundBooks;

    }

    @Override
    public boolean removeByName(String name) {

        int countOfDeleted = 0;
        for (int i = 0; i < schoolBooks.length; i++){
            if(schoolBooks[i].getName().equals(name)){
                schoolBooks[i] = null;
                countOfDeleted ++;
            }
        }

        if(countOfDeleted == 0){
            return false;
        }

        int countsOfSurvival = schoolBooks.length - countOfDeleted;
        SchoolBook[] bufferBooks = new SchoolBook[countsOfSurvival];
        for(int i = 0; i < schoolBooks.length; i++){
            if(schoolBooks[i] == null){
                continue;
            }
            countsOfSurvival --;
            bufferBooks[countsOfSurvival] = schoolBooks[i];
        }
        schoolBooks = bufferBooks;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }

}
