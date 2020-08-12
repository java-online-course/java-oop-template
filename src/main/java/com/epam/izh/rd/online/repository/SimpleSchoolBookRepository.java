package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks;

    public SimpleSchoolBookRepository() {

        this.schoolBooks = new SchoolBook[0];
    }

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tempSchoolBooksSave;
        tempSchoolBooksSave = new SchoolBook[count()+1];
        for (int i = 0; i < count(); i++) {
            tempSchoolBooksSave[i] = schoolBooks[i];
        }
        tempSchoolBooksSave[tempSchoolBooksSave.length-1] = book;
        schoolBooks = tempSchoolBooksSave;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int top =0;
        SchoolBook[] tempSchoolBooksFind = new SchoolBook[schoolBooks.length];
        for (int i = 0; i < count(); i++) {
            if (name.equals(schoolBooks[i].getName())) {
                tempSchoolBooksFind[top++] = schoolBooks[i];
            }
        }

        SchoolBook[] results = new SchoolBook[top];
        for (int i = 0; i < top; i++) {
            results[i] = tempSchoolBooksFind[i];
        }

        return results;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] copy = schoolBooks; // копирование исходного массива для мутации
        int len = copy.length;
        int countRemoved = 0;
        SchoolBook temp;
        if (findByName(name) != null){
            for (int i = 0; i < len-countRemoved; ) {
                if (copy[i].getName().equals(name)) {
                    countRemoved +=1;
                    for (int j = i; j < len-countRemoved; j++) {
                        temp = copy[j];
                        copy[j] = copy[j+1];
                        copy[j+1] = temp;

                    }
                }
                else
                    i++;
            }
            SchoolBook[] tempBooks = new SchoolBook[len - countRemoved];
            for (int i = 0; i < tempBooks.length; i++) {
                tempBooks[i] = copy[i];
            }
            schoolBooks = tempBooks;
            return true;
        }
        else
            return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
