package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import static java.util.Arrays.copyOf;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }

        Author[] authorsNew = copyOf(this.authors, this.authors.length + 1);
        authorsNew[authorsNew.length - 1] = author;
        this.authors = authorsNew;

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

        Author[] authorsNew = new Author[this.authors.length - 1];

        int shiftOfArrayElements = 0;
        for (int i = 0; i < this.authors.length; i++) {
            if (this.authors[i] != author) {
                authorsNew[i - shiftOfArrayElements] = this.authors[i];
            } else {
                shiftOfArrayElements++;
            }
        }

        this.authors = authorsNew;

        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
