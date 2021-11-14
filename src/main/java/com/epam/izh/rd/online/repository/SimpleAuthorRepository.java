package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] tmp = authors.clone();
            authors = new Author[tmp.length + 1];
            for (int i = 0; i < tmp.length; i++) {
                authors[i] = tmp[i];
            }
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (checkFullName(author, name, lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] tmpArr = authors.clone();
            authors = new Author[tmpArr.length - 1];
            int count = 0;
            for (Author value : tmpArr) {
                if (!(checkFullName(value, author.getName(), author.getLastName()))) {
                    authors[count] = value;
                    count++;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }

    private boolean checkFullName (Author author, String name, String lastName) {
        return author.getName().equals(name) && author.getLastName().equals(lastName);
    }
}
