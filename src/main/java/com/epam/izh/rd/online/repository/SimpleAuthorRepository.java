package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private  Author[] authors = new Author[]{} ;

    @Override
    public boolean save(Author author) {
        int saveCount = count() ;
        if (findByFullName(author.getName(), author.getLastName()) == null){
            Author[] saveAuthor = new Author[saveCount+1] ;
            saveAuthor[saveCount] = author ;
            authors = saveAuthor ;
            return true ;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author ;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null){
            Author[] removeAuthors = new Author[authors.length - 1] ;
            for (int i = 0 , j = 0 ; j<authors.length ; j++) {
                if (authors[j].getName().equals(author.getName()) && authors[j].getLastName().equals(author.getLastName()))
                    continue;
                removeAuthors[i++] = authors[j] ;
            }
            authors = removeAuthors ;
            return true ;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length ;
    }
}