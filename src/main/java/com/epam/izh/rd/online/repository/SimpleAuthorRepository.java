package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] temp = new Author[authors.length + 1];
        for(int i = 0; i < authors.length; i++) {
            temp[i] = authors[i];
        }
        temp [authors.length] = author;
        authors = temp;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author:authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            Author[] temp = new Author[authors.length - 1];
            int j = 0;
            for (Author i : authors) {
                if (i!= findByFullName(author.getName(), author.getLastName())) {
                    temp[j] = i;
                    j++;
                }
            }
            authors = temp;
            return true;
        }
    }
    @Override
    public int count() {
        return authors.length;
    }
}
