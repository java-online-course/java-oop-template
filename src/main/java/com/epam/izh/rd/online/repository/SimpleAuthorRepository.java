package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Objects;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        boolean isAuthor = findByFullName(author.getName(), author.getLastName()) == null;
        if (isAuthor) {
            Author[] tempAuthors = new Author[authors.length + 1];
            System.arraycopy(authors, 0, tempAuthors, 0, authors.length);
            tempAuthors[tempAuthors.length - 1] = author;
            authors = tempAuthors;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        boolean isName = false;
        boolean isLastName = false;
        for (Author authorForEach : authors) {
//            Arrays.stream(authors).forEach(authorForEach.getName()::equals);
            isName = name == authorForEach.getName();
            isLastName = lastname == authorForEach.getLastName();
            if (isName && isLastName) {
                return authorForEach;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean isAuthor = author.equals(findByFullName(author.getName(), author.getLastName()));
        if (isAuthor) {
            Author[] tempAuthors = Arrays.stream(authors)
                    .filter(authorDel -> !author.equals(authorDel))
                    .toArray(Author[]::new);
            authors = tempAuthors;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
