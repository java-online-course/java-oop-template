package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;



public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] savedAuthors = new Author[count() + 1];
        if (count() > 0) {
            System.arraycopy(authors, 0, savedAuthors, 0, count());
        }
        savedAuthors[count()] = author;
        authors = savedAuthors;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] removedAuthors = new Author[count() - 1];
        int newSize = 0;
        for (Author author1 : authors) {
            if (!author1.equals(author)) {
                removedAuthors[newSize] = author1;
                newSize++;
            }
        }
        authors = removedAuthors;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
