package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] authorsAfterSave = new Author[authors.length + 1];
            for (int i = 0; i <= authors.length; i++) {
                if (i == authors.length) {
                    authorsAfterSave[i] = author;
                } else {
                    authorsAfterSave[i] = authors[i];
                }
            }
            authors = authorsAfterSave;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author result = null;
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                result = author;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Author author) {
        boolean result = false;
        int index = 0;
        while (index < authors.length) {
            if (authors[index].getName().equals(author.getName()) && authors[index].getLastName().equals(author.getLastName())) {
                if (authors.length > 1) {
                    if (index == 0) {
                        Author[] authorsAfterRemove = new Author[authors.length - 1];
                        System.arraycopy(authors, 1, authorsAfterRemove, 0, authorsAfterRemove.length);
                        authors = authorsAfterRemove;
                    } else if (index == authors.length - 1) {
                        Author[] authorsAfterRemove = new Author[authors.length - 1];
                        System.arraycopy(authors, 0, authorsAfterRemove, 0, authors.length - 1);
                        authors = authorsAfterRemove;
                    } else {
                        Author[] authorsAfterRemove = new Author[authors.length - 1];
                        for (int i = 0; i < authors.length; i++) {
                            if (i > index) {
                                authorsAfterRemove[i - 1] = authors[i];
                            }
                            if (i == index) {
                                continue;
                            }
                            if (i < index) {
                                authorsAfterRemove[i] = authors[i];
                            }
                        }
                        authors = authorsAfterRemove;
                    }
                } else {
                    authors = new Author[0];
                }
                result = true;
                break;
            }
            index++;
        }


        return result;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
