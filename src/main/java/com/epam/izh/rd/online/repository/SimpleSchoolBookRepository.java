package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks= {};
    @Override
    public boolean save(SchoolBook book) {

        try {

            SchoolBook[] fieldForCopy = new SchoolBook[count()];
            System.arraycopy(schoolBooks, 0, fieldForCopy, 0, schoolBooks.length);
            schoolBooks = new SchoolBook[count()+1];
            System.arraycopy(fieldForCopy, 0, schoolBooks, 0, fieldForCopy.length);

            schoolBooks[count()-1] = book;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] results = new SchoolBook[0];
        for (int i=0;i<schoolBooks.length;i++){
            if(schoolBooks[i].getName() ==name){
                SchoolBook[] fieldForCopy = new SchoolBook[results.length + 1];
                System.arraycopy(results, 0, fieldForCopy, 0, results.length);
                results = new SchoolBook[fieldForCopy.length];
                System.arraycopy(fieldForCopy, 0, results, 0, results.length);

                results[results.length-1]=schoolBooks[i];
            }
        }
        return results;
    }

    @Override
    public boolean removeByName(String name) {
        if(findByName(name).length!=0){
            SchoolBook[] fieldForCopy = new SchoolBook[count() - findByName(name).length];
            for (int i=0; i<count(); i++){
                if(schoolBooks[i].getName() != name){
                    fieldForCopy[i]=schoolBooks[i];

                }
            }
            schoolBooks = fieldForCopy;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
