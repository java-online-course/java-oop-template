package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (!isAuthorNotExist(author)) {
            return false;
        }

        Author[] resultArr = Arrays.copyOf(authors, authors.length + 1);
        resultArr[resultArr.length - 1] = author;
        authors = resultArr;

        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) &&
                    author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (isAuthorNotExist(author)) {
            return false;
        }

        Author[] tempArr = Arrays.stream(authors).filter(theAuthor ->
                !theAuthor.getName().equals(author.getName())
                        || !theAuthor.getLastName().equals(author.getLastName())).toArray(Author[]::new);
        authors = tempArr;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }

    private boolean isAuthorNotExist(Author author) {
        return findByFullName(author.getName(), author.getLastName()) == null;
    }
}
