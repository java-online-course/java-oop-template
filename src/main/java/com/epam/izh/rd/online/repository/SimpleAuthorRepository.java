package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (author == findByFullName(author.getName(), author.getLastName())) {
            return  false;
        } else {
            Author[] fieldForCopy = new Author[authors.length + 1];
            System.arraycopy(authors, 0, fieldForCopy, 0, authors.length);
            authors = new Author[fieldForCopy.length];
            System.arraycopy(fieldForCopy, 0, authors, 0, authors.length);
            authors[authors.length - 1] = author;
            return  true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if ((authors[i].getName() == name) && (authors[i].getLastName() == lastname)) {
                return authors[i];
            }
        }
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (findByFullName(author.getName(), author.getLastName()) != null) {
                Author[] fieldForCopy = new Author[authors.length];
                System.arraycopy(authors, 0, fieldForCopy, 0, authors.length);
                authors = new Author[fieldForCopy.length - 1];
                System.arraycopy(fieldForCopy, i + 1, authors, i, authors.length);
                return true;
            }
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
