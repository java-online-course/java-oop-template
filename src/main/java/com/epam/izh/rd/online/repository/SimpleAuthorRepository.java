package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        boolean addAuthor = false;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = ArrayUtils.addAll(authors, author);
            addAuthor = true;
        }
        return addAuthor;
    }
    @Override
    public Author findByFullName(String name, String lastname) {
        Author[] findAuthor = {null};
        if (authors.length != 0) {
            for (Author author : authors) {
                if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                    findAuthor[0] = author;
                }
            }
        }
        return findAuthor[0];
    }

    @Override
    public boolean remove(Author author) {
        boolean deleteAuthor = false;
        for (int i = 0; i < authors.length; i++) {
            if (author.getName().equals(authors[i].getName()) &&
                    author.getLastName().equals(authors[i].getLastName())) {
                authors = ArrayUtils.removeElement(authors, authors[i]);
                deleteAuthor = true;
            }
        }

        return deleteAuthor;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
