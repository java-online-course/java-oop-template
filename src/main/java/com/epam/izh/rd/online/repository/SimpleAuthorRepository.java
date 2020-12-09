package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private static int count = 0;
    private Author[] authors = new Author[count];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] authors1 = new Author[0];
            authors1 = Arrays.copyOf(authors, count);
            count++;
            authors = Arrays.copyOf(authors1, count);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
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
        Author[] authors1 = new Author[0];
        authors1 = Arrays.copyOf(authors, count);
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            for (int i = 0; i < authors1.length; i++) {
                if (authors1[i].getName().equals(author.getName()) && authors1[i].getLastName().equals(author.getLastName())) {
                    Author author1 = new Author();
                    author1 = authors1[i];
                    if (authors1[i].equals(author1)) {
                        authors1[i] = authors1[authors1.length - 1];
                        authors1[authors1.length - 1] = author1;
                        count--;
                    }
                }
            }
            authors = Arrays.copyOf(authors1, count);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return count;
    }
}
