package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] authorsBuffer = new Author[authors.length + 1];
        for(int i = 0; i < authors.length; i++) {
            authorsBuffer[i] = authors[i];
        }
        authorsBuffer [authors.length] = author;
        authors = authorsBuffer;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for(Author author : authors) {
            if(author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            Author[] authorsBuffer = new Author[authors.length - 1];
            int i = 0;
            for (Author authorFound : authors) {
                if (authorFound != findByFullName(author.getName(), author.getLastName())) {
                    authorsBuffer[i] = authorFound;
                    i++;
                }
            }
            authors = authorsBuffer;
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}