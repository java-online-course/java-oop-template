package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {

        if(author.equals(findByFullName(author.getName(), author.getLastName()))){
            return false;
        }else{
            int i = 0;
            authors[i]= new Author();
            authors[i] = author;
            i++;
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        for (int i = 0; i < authors.length; i++) {
            if (name.equals(authors[i].getName()) && lastname.equals(authors[i].getLastName())){
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if(author.equals(findByFullName(author.getName(), author.getLastName()))){
            author = null;
            for (int i = 2; i < authors.length-1; i++) {
                authors[i-1] = authors[i];
                authors[i] = null;

            }
            return true;
        }else return false;
    }

    @Override
    public int count() {

        return 0;
    }
}
