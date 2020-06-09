package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;


public class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] res = new Author[count() + 1];
            for (int i = 0; i < count(); i++) {
                res[i] = authors[i];
            }
            res[count()] = author;
            authors = res;
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Author findByFullName(String name, String lastname) {

        for (Author author: authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }

        return null;
    }


    @Override
    public boolean remove(Author author) {

        if (count() == 0) {
            return false;
        }
        Author[] res = new Author[count() - 1];
        int n = 0;
        for (Author aut: authors) {
            if (!aut.getName().equals(author.getName()) &&
                !aut.getLastName().equals(author.getLastName())) {
                res[n] = aut;
                n++;
            }
        }

        authors = res;
        return n > 0 ? false : true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}