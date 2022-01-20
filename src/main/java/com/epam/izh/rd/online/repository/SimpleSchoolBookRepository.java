package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private static SchoolBook[] schoolBooks = new SchoolBook[0];

    public SimpleSchoolBookRepository() {
    }

    public static SchoolBook[] getSchoolBooks() {
        return schoolBooks;
    }

    @Override
    public boolean save(SchoolBook book) {
        Author[] authors = SimpleAuthorRepository.getAuthors();

        for (Author author : authors) {
            if (Objects.equals(author.getName(), book.getAuthorName()) && Objects.equals(author.getLastName(), book.getAuthorLastName())) {
                SchoolBook[] temp = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
                temp[temp.length - 1] = book;
                schoolBooks = Arrays.copyOf(temp, temp.length);
                return true;
            }
        }

        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        for (SchoolBook schoolBook : schoolBooks) {
            if (Objects.equals(schoolBook.getName(), name)) {
                for (int i = 0; i < schoolBooks.length; i++) {
                    if (Objects.equals(schoolBooks[i].getName(), name)) {
                        SchoolBook[] tempBooks = new SchoolBook[schoolBooks.length - 1];
                        int remainingElements = schoolBooks.length - ( i + 1 );
                        System.arraycopy(schoolBooks, 0, tempBooks, 0, i);
                        System.arraycopy(schoolBooks, i + 1, tempBooks, i, remainingElements);
                        schoolBooks = Arrays.copyOf(tempBooks, tempBooks.length);
                        i--;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}

