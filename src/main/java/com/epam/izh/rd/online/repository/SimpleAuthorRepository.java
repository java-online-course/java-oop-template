package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] copy = new Author[authors.length + 1];
            System.arraycopy(authors, 0, copy, 0, authors.length);
            authors = copy;
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;

        }

    }


    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName() == name && authors[i].getLastName() == lastname) {
                return authors[i];
            }
        }
        return null;

    }


    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName() == author.getName() && authors[i].getLastName() == author.getLastName()) {
                author = authors[0];
                authors[0] = authors[i];
                authors[i] = author;
                Author[] copy = new Author[authors.length - 1];
                System.arraycopy(authors, 1, copy, 0, authors.length - 1);
                authors = copy;
                return true;
            }

        }
        return false;

    }

    public int count() {
        int numberOfAuthors = authors.length;
        return numberOfAuthors;
    }
}
