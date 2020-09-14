package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author authorInArray : authors) {
            if (authorInArray.getName().equals(name) && authorInArray.getLastName().equals(lastname)) {
                return authorInArray;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        Author[] tempAuthors;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            tempAuthors = new Author[count() - 1];
            int i = 0;
            for (Author authorInArray : authors) {
                if (authorInArray != findByFullName(author.getName(), author.getLastName())) {
                    tempAuthors[i] = authorInArray;
                    i++;
                }
            }
        }
        authors = tempAuthors;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}