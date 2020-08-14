package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        int len = authors.length;
        authors = Arrays.copyOf(authors, len + 1);
        authors[len] = new Author(author.getName(), author.getLastName(), author.getBirthdate(), author.getCountry());
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        Author authorToRemoveInList = findByFullName(author.getName(), author.getLastName());
        if (authorToRemoveInList == null) {
            return false;
        }
        Author[] newAuthors = new Author[authors.length - 1];
        for (int i = 0; i < authors.length; i++) {
            if (!authors[i].equals(authorToRemoveInList)) {
                newAuthors[i].setName(author.getName());
                newAuthors[i].setLastName(author.getLastName());
                newAuthors[i].setBirthdate(author.getBirthdate());
                newAuthors[i].setCountry(author.getCountry());
            }
        }
        setAuthors(newAuthors);
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
