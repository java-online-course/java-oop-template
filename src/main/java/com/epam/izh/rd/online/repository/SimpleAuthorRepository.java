package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }

        Author[] authorsUpdated = new Author[count() + 1];
        for (int i = 0; i < count(); i++) {
            authorsUpdated[i] = authors[i];
        }
        authorsUpdated[count()] = author;
        authors = authorsUpdated;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author : authors) {
            if (author.getName() == name && author.getLastName() == lastName) {
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

        Author[] authorsUpdated = new Author[count() - 1];
        for (int i = 0, authorCounter = 0; i < count(); i++, authorCounter++) {
            if (authors[i].getName() == author.getName() && authors[i].getLastName() == author.getLastName()) {
                authorCounter--;
                continue;
            }
            authorsUpdated[authorCounter] = authors[i];
        }
        authors = authorsUpdated;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
