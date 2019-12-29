package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        Author currentAuthor = findByFullName(author.getName(), author.getLastName());
        if (currentAuthor != null) {
            return false;
        }
        int size = authors.length + 1;
        Author[] newAuthors = new Author[size];
        System.arraycopy(authors, 0, newAuthors, 0, authors.length);
        newAuthors[authors.length] = author;
        authors = newAuthors;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (name.equals(author.getName()) && lastname.equals(author.getLastName()))
                return author;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int number = 0; number < authors.length; number++) {
            if (author.equals(authors[number])) {
                Author[] newAuthors = new Author[authors.length - 1];
                for (int i = number + 1; i < authors.length; i++) {
                    authors[i - 1] = authors[i];
                }
                System.arraycopy(authors, 0, newAuthors, 0, newAuthors.length);
                authors = newAuthors;
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