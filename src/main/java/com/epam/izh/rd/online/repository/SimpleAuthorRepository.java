package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(),author.getLastName())!=null) return false;
        authors = Arrays.copyOf(authors,authors.length+1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().toUpperCase().equals(name.toUpperCase()) &&
            author.getLastName().toUpperCase().equals(lastname.toUpperCase())) return author;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(),author.getLastName())==null) return false;
        int count = 0;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName() == null) {
                count = i;
                break;
            }
        }
        for (int i = count; i < authors.length - 1; i++) {
            authors[i-1] = authors[i];
        }
        authors = Arrays.copyOf(authors,authors.length - 1);
        return  true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
