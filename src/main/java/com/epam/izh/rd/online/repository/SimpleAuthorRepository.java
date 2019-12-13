package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    //TODO Удалить все, что для тестов и вернуть private
    private Author[] authors = {}; //Для тестов при написании

    @Override
    public boolean save(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equalsIgnoreCase(author.getName()) && authors[i].getLastName().equalsIgnoreCase(author.getLastName())) {
                return false;
            }
        }
        Author[] authorsTemp = new Author[authors.length + 1];
        System.arraycopy(authors, 0, authorsTemp, 0, authors.length);
        authorsTemp[authorsTemp.length - 1] = author;
        authors = authorsTemp;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (authors == null) {
            return null;
        }

        for (Author nameAuthor : authors) {
            if (nameAuthor.getName().equalsIgnoreCase(name) && nameAuthor.getLastName().equalsIgnoreCase(lastname)) {
                return nameAuthor;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equalsIgnoreCase(author.getName()) && authors[i].getLastName().equalsIgnoreCase(author.getLastName())) {

                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                Author[] authorsTemp = new Author[authors.length - 1];
                System.arraycopy(authors, 0, authorsTemp, 0, authors.length - 1);
                authors = authorsTemp;
                return true;
            }
        }

        return false;
    }

    @Override
    public int count() {
        if (authors == null) {
            return 0;
        } else {
            return authors.length;
        }
    }
}
