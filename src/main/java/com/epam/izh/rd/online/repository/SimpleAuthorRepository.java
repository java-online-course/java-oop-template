package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.apache.commons.lang3.ArrayUtils.toArray;

public class SimpleAuthorRepository implements AuthorRepository{

    private Author[] authors = new Author[] {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) return false;
        //authors = Arrays.stream(authors).toArray(Author[]::new);
        authors = Stream.concat(Arrays.stream(authors), Stream.of(author)).toArray(Author[]::new);
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author : authors) {
            if (author.getName().toUpperCase().equals(name.toUpperCase()) &&
                    author.getLastName().toUpperCase().equals(lastName.toUpperCase())) return author;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int countBefore = count();
        authors = (Author[]) Arrays.stream(authors).filter(o -> !o.equals(author)).toArray();
        return countBefore != count();
    }

    @Override
    public int count() {
        return authors.length;
    }
}
