package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;



import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors;

    @Override
    public boolean save(Author author) {
        if (authors != null) {
            if (findByFullName(author.getName(), author.getLastName()) == null) {
                authors = Arrays.copyOf(authors, authors.length + 1);
                authors[authors.length - 1] = author;
                return true;
            } else {
                return false;
            }
        } else {
            authors = new Author[1];
            authors[0] = author;
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (authors != null) {
            for (Author author : authors) {
                if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                    return author;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (authors != null){
            for (int i = 0; i <authors.length ; i++) {
                if (findByFullName(authors[i].getName(), authors[i].getLastName()) != null){
                    authors [i] = authors[authors.length - 1];
                    authors = Arrays.copyOf(authors, authors.length - 1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}

