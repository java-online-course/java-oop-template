package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            if (authors == null) {
                authors = new Author[1];
                authors[0] = author;
            } else {
                Author[] arrayCopy = Arrays.copyOf(authors, authors.length + 1);
                arrayCopy[arrayCopy.length - 1] = author;
                authors = arrayCopy;
            }
            return true;
        }

    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            if (authors.length == 1) {
                authors = new Author[0];
                return true;
            }
            for (int i = 0; i < authors.length - 1; i++) {
                if (findByFullName(author.getName(), author.getLastName()).equals(authors[i])) {
                    Author[] arrayCopy = new Author[authors.length - 1];
                    System.arraycopy(authors, 0, arrayCopy, 0, i);
                    System.arraycopy(authors, i + 1, arrayCopy, i, authors.length - i - 1);
                    authors = arrayCopy;
                }
            }
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
