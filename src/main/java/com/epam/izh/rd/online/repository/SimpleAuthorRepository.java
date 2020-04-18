package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            Author[] tempAuthor = new Author[authors.length + 1];
            System.arraycopy(authors, 0, tempAuthor, 0, authors.length);
            tempAuthor[tempAuthor.length - 1] = author;
            authors = new Author[tempAuthor.length];
            System.arraycopy(tempAuthor, 0, authors, 0, tempAuthor.length);
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author auth : authors) {
            if (auth.getName().equals(name) && auth.getLastName().equals(lastname)) {
                return auth;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int searchedElement = 0;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            for (int i = 0; i < authors.length; i++) {
                if (authors[i] == author) {
                    searchedElement = i;
                    break;
                }
            }
            Author[] authorsTemp = new Author[authors.length - 1];
            System.arraycopy(authors, 0, authorsTemp, 0, searchedElement);
            System.arraycopy(authors, searchedElement + 1, authorsTemp, searchedElement, authors.length - searchedElement - 1);
            authors = new Author[authorsTemp.length];
            System.arraycopy(authorsTemp, 0, authors, 0, authorsTemp.length);
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}