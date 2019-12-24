package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

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
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] newAuthors = Arrays.copyOf(authors, authors.length + 1);
            newAuthors[authors.length] = author;
            authors = newAuthors;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] newAuthors = Arrays.copyOf(authors, authors.length - 1);
            int j = 0;
            for (Author author1 : authors) {
                if (!author1.getName().equals(author.getName()) && author1.getLastName().equals(author.getLastName())) {
                    newAuthors[j] = author1;
                    j++;
                }
            }
            authors = newAuthors;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
