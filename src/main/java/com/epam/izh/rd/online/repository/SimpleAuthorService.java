package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Objects;

public class SimpleAuthorService implements AuthorRepository {

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
            isName = Arrays.stream(authors).anyMatch(name::equals);
            isLastName = Arrays.stream(authors).anyMatch(lastname::equals);
            if (isName && isLastName) {
                return authorForEach;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean isName = false;
        boolean isLastName = false;
        boolean isAuthor = Objects.nonNull(findByFullName(author.getName(), author.getLastName()));
        if (isAuthor) {
            for (Author authorForEach : authors) {
                isName = Arrays.stream(authors).anyMatch(author.getName()::equals);
                isLastName = Arrays.stream(authors).anyMatch(author.getLastName()::equals);
                if (isName && isLastName) {
                    authorForEach = null;
                    Author[] tempAuthors = Arrays.stream(authors).filter(Objects::nonNull).toArray(Author[]::new);
                    authors = tempAuthors;
                    return false;
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
