package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Objects;

public class SimpleAuthorRepository implements AuthorRepository {
    private static Author[] authors = new Author[0];

    public SimpleAuthorRepository() {
    }

    public static Author[] getAuthors() {
        return authors;
    }

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] temp = Arrays.copyOf(authors, authors.length + 1);
            temp[temp.length - 1] = author;
            authors = Arrays.copyOf(temp, temp.length);
            return true;
        }

        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (Objects.equals(author.getName(), name) && Objects.equals(author.getLastName(), lastname)) return author;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int numOfAuthor = -1;
        for (int i = 0; i < authors.length; i++) {
            if (Objects.equals(author.getName(), authors[i].getName()) && Objects.equals(author.getLastName(), authors[i].getLastName())) numOfAuthor = i;
        }

        if (numOfAuthor != -1) {
            for(int i = numOfAuthor; i < authors.length - 1; i++) {
                Author temp = authors[i];
                authors[i] = authors[i + 1];
                authors[i + 1] = temp;
            }
            authors = Arrays.copyOf(authors, authors.length - 1);
            return true;
        }


        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
