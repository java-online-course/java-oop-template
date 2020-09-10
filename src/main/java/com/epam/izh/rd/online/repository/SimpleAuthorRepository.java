package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[10];
    private int count = 0;

    public SimpleAuthorRepository() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean save(Author author) {
        if (count == 0) {
            authors[count] = author;
            count++;
            return true;
        } else if (author.equals(findByFullName(author.getName(), author.getLastName()))) {
            return false;
        } else {
            authors[count] = author;
            count++;
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < count; i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean a = false;
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                authors[i] = null;
                count--;
                a = true;
                flag = i;
            }
        }
        if (a) {
            for (int i = flag; i < count; i++) {
                authors[i] = authors[i + 1];
            }
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return count;
    }
}
