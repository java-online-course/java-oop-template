package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = ArrayUtils.add(schoolBooks, book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] schoolBookTemp = new SchoolBook[]{};
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)){
                ArrayUtils.add(schoolBookTemp, schoolBooks[i]);
            }
        }
        return schoolBookTemp;
    }

    @Override
    public boolean removeByName(String name) {
        int lastSize = schoolBooks.length;
        schoolBooks = Arrays.stream(schoolBooks)
                .filter(book -> !book.getName().equalsIgnoreCase(name))
                .toArray(SchoolBook[]::new);
        return lastSize > schoolBooks.length;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}