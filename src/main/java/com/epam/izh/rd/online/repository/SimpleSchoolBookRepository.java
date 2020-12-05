package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private  SchoolBook[] schoolBooks = {};
    @Override
    public boolean save(SchoolBook book) {
            SchoolBook[] a = new SchoolBook[count() + 1];
            for(int i=0; i<count(); i++){
                a[i] = schoolBooks[i];
            }
            a[this.count()]= book;
            schoolBooks =a;
            return true;
    }


    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook []a;
        int k=0;
        for(int i=0; i< count(); i++){
            if(schoolBooks[i].getName()==name){
                k++;
            }
        }
        if (k==0){
            a = new SchoolBook[0];
        } else {
            a=new SchoolBook[k];
            for (int i=0; i<count(); i++){
                if(schoolBooks[i].getName() == name){
                    a[i] = schoolBooks[i];
                }
            }
        }
        return a;
    }

    @Override
    public boolean removeByName(String name) {

        if(findByName(name).length==0){
            return false;
        } else {
            SchoolBook[] a = new SchoolBook[count() - findByName(name).length];
            int k=0;
            for (int i=0; i<count(); i++){
                if(schoolBooks[i].getName() != name){
                    a[i]=schoolBooks[i];
                }
            }
            schoolBooks=a;
            return true;
        }

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
