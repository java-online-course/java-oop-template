package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

        ArrayList<SchoolBook> buffList = new ArrayList<>();
        for(SchoolBook element : schoolBooks){
            if(element.getName().equals(name)){
                buffList.add(element);
            }
        }

        return buffList.toArray(new SchoolBook[buffList.size()]);
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

        SchoolBook[] bufferBooks = new SchoolBook[schoolBooks.length - countOfDeleted];
        for(int i = 0; i < schoolBooks.length; i++){
            countOfDeleted --;
            bufferBooks[schoolBooks.length - countOfDeleted] = schoolBooks[i];
        }
        schoolBooks = bufferBooks;

        return true;
    }

    @Override
    public int count() {
        return 0;
    }

//    public <T> void addItemToArray(T[] ary){
//
//        if(ary.length == 0){
//            T[] ary1 = new <T>[8];
//        }
//
//    }

}
