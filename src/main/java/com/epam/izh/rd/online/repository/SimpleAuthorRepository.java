package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        } else return false;
    }


    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().toUpperCase().equals(name.toUpperCase()) && author.getLastName().toUpperCase().equals(lastname.toUpperCase())) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == author) {
            for (int i = 0; i < authors.length; i++) {
                if (authors[i] == author) {
                    for (int j = i; j < authors.length - 1; j++) {
                        Author temp = authors[j];
                        authors[j] = authors[j + 1];
                        authors[j + 1] = temp;
                    }
                }
            }
            authors = Arrays.copyOf(authors, authors.length - 1);
            return true;
        } else return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
