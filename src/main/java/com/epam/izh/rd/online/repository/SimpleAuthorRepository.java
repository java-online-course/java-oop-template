package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        Author existedAuthorOrNull = findByFullName(author.getName(), author.getLastName());
        if (Objects.nonNull(existedAuthorOrNull)) {
            return false;
        }
        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Optional<Author> author = Arrays.stream(authors)
                .filter(e ->
                        e.getName().equals(name) && e.getLastName().equals(lastname))
                .findFirst();
        return author.orElse(null);
    }

    @Override
    public boolean remove(Author author) {
        Author existedAuthorOrNull = findByFullName(author.getName(), author.getLastName());
        if (Objects.isNull(existedAuthorOrNull)) {
            return false;
        }

        authors = Arrays.stream(authors)
                .filter(e ->
                        !e.getName().equals(existedAuthorOrNull.getName()) &&
                                !e.getLastName().equals(existedAuthorOrNull.getLastName()))
                .toArray(Author[]::new);
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
