package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

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

        SchoolBook[] ss = new SchoolBook[buffList.size()];
        ss = (SchoolBook[]) buffList.toArray();
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
