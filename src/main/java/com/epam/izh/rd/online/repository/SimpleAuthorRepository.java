package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] increasedAuthors = Arrays.copyOf(this.authors, this.count() + 1);
        increasedAuthors[this.count()] = author;
        this.authors = increasedAuthors;

        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < this.count(); i++) {
            if (this.authors[i].getName().equals(name)
                    && this.authors[i].getLastName().equals(lastname)) {
                return this.authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] decreasedAuthors = new Author[this.count() - 1];
        int posToInsertExistingAuthor = 0;
        for (int i = 0; i < this.count(); i++) {
            if (this.authors[i].getName().equals(author.getName()) &&
                    this.authors[i].getLastName().equals(author.getLastName())) {
                posToInsertExistingAuthor--;
            } else {
                decreasedAuthors[posToInsertExistingAuthor] = this.authors[i];
                posToInsertExistingAuthor++;
            }
        }
        this.authors = decreasedAuthors;

        return true;
    }

    @Override
    public int count() {
        return this.authors.length;
    }
}

