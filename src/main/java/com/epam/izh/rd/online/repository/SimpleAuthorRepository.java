package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Optional;

public class SimpleAuthorRepository implements AuthorRepository{

    private Author[] authors = new Author[] {};

    @Override
    public boolean save(Author author) {
        findByFullName(author.getName(), author.getLastName());
        authors = (Author[]) Arrays.stream(authors).toArray();
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        name = name.toUpperCase();
        lastName = name.toUpperCase();
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastName)) return author;
        }
        return Optional<Author>;
    }

    @Override
    public boolean remove(Author author) {
        int countBefore = count();
        authors = (Author[]) Arrays.stream(authors).filter(o -> !o.equals(author)).toArray();
        return countBefore == count() ? false : true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
