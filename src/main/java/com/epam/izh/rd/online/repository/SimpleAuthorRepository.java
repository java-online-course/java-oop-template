package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[1];

    @Override
    public boolean save(Author author) {
        if (authors[0] == null) {
            Author[] authorSave = new Author[authors.length];
            authorSave[0] = author;
            authors = authorSave;

            return true;
        } else if (findByFullName(author.getName(), author.getLastName()) == null) {

            Author[] authorSecondSave = Arrays.copyOf(authors, authors.length + 1);

            for (int i = 1; i < authorSecondSave.length; i++) {
                if (authorSecondSave[i] == null)
                    authorSecondSave[i] = author;
            }
            authors = authorSecondSave;

            return true;
        } else
            return false;

    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author result = null;
        for (Author author : authors) {
            if (author == null) {
                return null;
            }
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                result = author;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author findAuthor = findByFullName(author.getName(), author.getLastName());

        Author[] authorRemove = Arrays.copyOf(authors, authors.length);
        for (int i = 0; i < authorRemove.length; i++) {
            if (authorRemove[i].equals(findAuthor)) {
                authorRemove[i] = null;
            }
        }
        if (authorRemove[0] == null) {
            authorRemove[0] = authorRemove[authorRemove.length - 1];
            authorRemove[authorRemove.length - 1] = null;
            Author[] authorRem = Arrays.copyOf(authorRemove, authorRemove.length - 1);
            authors = authorRem;

        } else if (authorRemove[authorRemove.length - 1] == null) {
            Author[] authorRem = Arrays.copyOf(authorRemove, authorRemove.length - 1);
            authors = authorRem;
        } else
            for (int i = 1; i < authorRemove.length; i++) {
                if (authorRemove[i] == null) {
                    authorRemove[i] = authorRemove[authorRemove.length - 1];
                    authorRemove[authorRemove.length - 1] = null;
                    Author[] authorRem = Arrays.copyOf(authorRemove, authorRemove.length - 1);
                    authors = authorRem;
                }
            }
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}