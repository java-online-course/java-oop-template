package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] tempArrayAuthor;
        int lengthArray = count();
        tempArrayAuthor = Arrays.copyOf(authors, lengthArray + 1);
        tempArrayAuthor[lengthArray + 1] = author;
        authors = Arrays.copyOf(tempArrayAuthor, lengthArray + 1);
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
//        if (findByFullName(author.getName(), author.getLastName()) == null) {
//
//        }
        for (int i = 0; i < count(); i++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                authors = ArrayUtils.removeElement(authors, i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
