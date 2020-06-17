package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }

        Author[] authors = new Author[this.authors.length + 1];

        for (int i = 0; i < this.authors.length; i++) {
            authors[i] = this.authors[i];
        }

        authors[authors.length - 1] = author;
        this.authors = authors;

        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author: authors) {
            if ((author.getName() == name) && (author.getLastName() == lastName)) {
                return author;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }

        Author[] authors = new Author[this.authors.length - 1];

        int j = 0;
        for (int i = 0; i < this.authors.length; i++) {
            if (this.authors[i] != author) {
                authors[j] = this.authors[i];
                j++;
            }
        }

        this.authors = authors;

        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
