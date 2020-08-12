package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;

class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null){
            authors = ArrayUtils.add(authors, author);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)){
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null){
            return false;
        } else {
            authors = ArrayUtils.remove(authors, ArrayUtils.indexOf(authors, author));
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}