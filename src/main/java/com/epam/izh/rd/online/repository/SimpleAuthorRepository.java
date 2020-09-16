package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        for (Author value : authors) {
            if (value.equals(author)) {
                return false;
            }
        }

        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author value : authors) {
            if (value.getName().equals(name) && value.getLastName().equals(lastname)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                authors[i] = null;

                System.arraycopy(authors, 0, authors, 0, i);
                System.arraycopy(authors, i + 1, authors, i, authors.length - 1 - i);
                authors = Arrays.copyOf(authors, authors.length - 1);

                return true;
            }
        }


        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
