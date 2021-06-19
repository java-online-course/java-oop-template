package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;



public class SimpleAuthorRepository implements AuthorRepository{

    Author[] authors= {};

    @Override
    public boolean save(Author author) {

        boolean result = false;

        if(findByFullName(author.getName(),author.getLastName())==null){
            Author[]temp = new Author[authors.length];
            System.arraycopy(authors, 0, temp, 0, authors.length);
            authors = new Author[authors.length+1];
            System.arraycopy(temp, 0, authors, 0, temp.length);
            authors[authors.length-1]=author;
            result = true;
        }

        return result;
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        for (Author author:authors) {
            if(author.getName().equals(name)&&author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean exist = false;
        for (int i = 0; i < authors.length; i++) {
            if(author.equals(authors[i])){
                exist = true;
                authors[i]=authors[authors.length-1];
                break;
            }
        }

        if(exist){
            Author[] temp = new Author[authors.length-1];
            System.arraycopy(authors,0,temp,0,temp.length);
            authors = new Author[temp.length];
            System.arraycopy(temp,0,authors,0,temp.length);
        }

        return exist;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
