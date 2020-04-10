package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    public SimpleAuthorRepository() {
    }

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
                Author[] testArr = new Author[count()+1];
                for (int i = 0; i < count();i++) {
                    testArr[i] = authors[i];
                }
                testArr[count()] = author;
                authors = testArr;
                return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        String AllName = name + lastname;
        for (int i = 0; i < count(); i++) {
            if (AllName.equals(authors[i].getName() + authors[i].getLastName())) {
                return authors[i];
            }
        }
        return null;
    }


    @Override
    public boolean remove(Author author) {
        if (author != null) {
            int availability = 0;
            int size = 0;
            for (int i = 0; i < count(); i++) {
                if (this.authors[i] == author) {
                    availability++;
                } else {
                }
                size++;
            }
            size--;
            if (availability > 0) {
                Author[] testArr = new Author[size];
                int j = 0;
                for (int i = 0; i < count(); i++) {
                    if (this.authors[i] != author) {
                        testArr[j] = this.authors[i];
                        j++;
                    } else {
                    }
                }
                this.authors = null;
                this.authors = testArr;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
