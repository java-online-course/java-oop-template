package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (author == findByFullName(author.getName(), author.getLastName())) {
            return false;
        } else {
            Author[] fieldForCopy = new Author[authors.length + 1];
            System.arraycopy(authors, 0, fieldForCopy, 0, count());
            authors = new Author[fieldForCopy.length];
            System.arraycopy(fieldForCopy, 0, authors, 0, count());
            authors[authors.length - 1] = author;
            return true;

        }
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
