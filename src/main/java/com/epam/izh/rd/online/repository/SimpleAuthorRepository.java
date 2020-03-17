package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {

        Author searchingAuthor = findByFullName(author.getName(), author.getLastName());
        if (searchingAuthor != null){
            return false;
        }

        Author[] bufferAuthors = new Author[authors.length + 1];
        for(int i = 0; i < authors.length; i++){
            bufferAuthors[i] = authors[i];
        }
        bufferAuthors[bufferAuthors.length - 1] = author;
        authors = bufferAuthors;

        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author:authors) {
            if(author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {

        int count = 0;
        boolean authorIsFound = false;

        for(int i = 0; i < authors.length; i++){
            if(authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())){
                authors[i] = null;
                authorIsFound = true;
            }
            else {
                count++;
            }
        }

        if(!authorIsFound){
            return false;
        }

        Author[] buffer = new Author[count];
        for(Author element : authors){
            if(element == null){
                continue;
            }
            count --;
            buffer[count] = element;
        }

        authors = buffer;

        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
