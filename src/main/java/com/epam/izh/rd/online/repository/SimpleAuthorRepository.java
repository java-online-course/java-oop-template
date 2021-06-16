package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];


    @Override
    public boolean save(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (findByFullName(author.getName(), author.getLastName()) != null) {
                return false;
            }
        }
        Author[] newAuthors = new Author[authors.length + 1];
        for (int i = 0; i < authors.length; i++) {
            newAuthors[i] = authors[i];
        }
        newAuthors[newAuthors.length - 1] = author;
        authors = newAuthors;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name)
                    && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (authors.length == 0) {
            return false;
        }
        for (int i = 0; i < authors.length; i++) {
            if (findByFullName(author.getName(), author.getLastName()) == null) {
                return false;
            }
        }

        Author[] newAuthors = new Author[authors.length - 1];
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(author.getName())
                    && authors[i].getLastName().equals(author.getLastName())) {
                authors[i] = null;
            }
        }


        int iterator = 0;

        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                newAuthors[iterator] = authors[i];
                iterator++;
            }
        }
        authors = newAuthors;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
