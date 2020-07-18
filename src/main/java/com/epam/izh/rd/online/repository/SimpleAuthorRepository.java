package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        boolean isSaved = false;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] tempAuthors = new Author[authors.length + 1];
            int position = 0;
            for (Author author1 : authors) {
                tempAuthors[position] = author1;
                position++;
            }
            tempAuthors[position] = author;
            authors = tempAuthors;
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author authorFound = null;
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                authorFound = author;
            }
        }
        return authorFound;
    }

    @Override
    public boolean remove(Author author) {
        ArrayList<Author> tempList = new ArrayList<>(Arrays.asList(authors));
        boolean authorFound = false;
        for (int i = 0; i < tempList.size(); i++) {
            if (author == tempList.get(i)) {
                tempList.remove(i);
                authorFound = true;
                break;
            }
        }
        authors = tempList.toArray(new Author[tempList.size()]);
        return authorFound;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
