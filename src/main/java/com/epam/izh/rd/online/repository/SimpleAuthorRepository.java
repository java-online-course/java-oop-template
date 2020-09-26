package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        for (Author value : authors) {
            if (author.getName().equals(value.getName()) && author.getLastName().equals(value.getLastName())) {
                return false;
            }
        }
        this.authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author : authors) {
            if (name.equals(author.getName()) && lastName.equals(author.getLastName())) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (author.getName().equals(authors[i].getName()) && author.getLastName().equals(authors[i].getLastName())) {
                Author temp = authors[authors.length - 1];
                authors[authors.length - 1] = authors[i];
                authors[i] = temp;
                this.authors = Arrays.copyOf(authors, authors.length - 1);
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
