package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.*;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        for (Author aut : authors) {
            if (author.equals(aut)) {
                return false;
            }
        }
        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return Arrays.stream(authors).
                filter(x -> x.getName().equals(name) && x.getLastName().equals(lastname)).
                findFirst().orElse(null);
    }

    @Override
    public boolean remove(Author author) {
        if (Arrays.asList(authors).contains(author)) {
            authors = Arrays.stream(authors).filter(x -> !(x.equals(author))).toArray(Author[]::new);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
