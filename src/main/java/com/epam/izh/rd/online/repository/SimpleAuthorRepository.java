package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(),author.getLastName()) != null) {
            return false;
        }
        authors = Arrays.copyOf(authors,authors.length + 1);
        authors[authors.length-1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if(author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int index = -1;
        for (int i = 0; i < authors.length; i++) {
            if(author.equals(authors[i])) index = i;
        }
        if(index == -1) return false;
        for(int i = index; i < authors.length ; i++) {
            if(i + 1 < authors.length) {
                authors[i] = authors[i+1];
            }
        }
        authors = Arrays.copyOf(authors,authors.length-1);
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
