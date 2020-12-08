package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;


public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    public boolean save(Author author) {

        for (int i = 0; i < authors.length; i++) {

            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                return false;
            }
        }

        this.authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }


    public Author findByFullName(String name, String lastname) {

        for (int i = 0; i < authors.length; i++) {

            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }

        return null;
    }


    public boolean remove(Author author) {

        for (int i = 0; i < authors.length; i++) {

            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {

                Author copy = authors[authors.length - 1];
                authors[authors.length - 1] = authors[i];
                authors[i] = copy;

                this.authors = Arrays.copyOf(authors, authors.length - 1);

                return true;
            }
        }

        return false;
    }

    public int count() {
        return authors.length;
    }
}
