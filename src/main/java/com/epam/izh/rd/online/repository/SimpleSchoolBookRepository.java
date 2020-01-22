package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>
{
    private SchoolBook[] schoolBooks =  {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tmpBooks = schoolBooks;
        int n = schoolBooks.length;
        schoolBooks = new SchoolBook[n + 1];
        for(int i = 0; i < n; i++)
            schoolBooks[i] = tmpBooks[i];
        schoolBooks[n] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        for(SchoolBook  v : schoolBooks)
        {
            if(v.getName() == name)
                count++;
        }
        SchoolBook[] tmpBooks = new SchoolBook[count];
        int index = 0;
        for(SchoolBook v : schoolBooks)
        {
            if(v.getName() == name)
                tmpBooks[index++] = v;
        }
        return tmpBooks;
    }

    @Override
    public boolean removeByName(String name) {
        boolean[] mask = new boolean[count()];
        int count = 0;
        for(int i = 0;i < schoolBooks.length; i++)
        {
            if(schoolBooks[i].getName() == name)
            {
                count++;
                mask[i] = true;
            }
        }
        if(count == 0)
            return false;
        SchoolBook[] tmpBooks = schoolBooks;
        schoolBooks = new SchoolBook[schoolBooks.length - count];
        int index = 0;
        for(int i = 0; i < tmpBooks.length; i++)
        {
            if(!mask[i])
                schoolBooks[index++] = tmpBooks[i];
        }
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
