package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors ={};

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(),author.getLastName())==null){
            Author[] a = new Author[this.authors.length + 1];
            for(int i=0; i<count(); i++){
                a[i] = authors[i];
            }
            a[this.count()]= author;

            authors = a;
            return true;
        }

        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        for(int i=0; i< authors.length; i++){
            if(authors[i].getName() == name && authors[i].getLastName()==lastname){
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        Author[] arr;
       if(findByFullName(author.getName(), author.getLastName())==null){
           return false;
       } else {
            arr = new Author[count()-1];
           Author a = findByFullName(author.getName(), author.getLastName());

           int i=0;
           while (i<count()){
               if(authors[i] != a ){
                   arr[i] = authors[i];
               }
               i++;
           }
       }
       authors =arr;
       return true;

    }

    @Override
    public int count() {
        return authors.length;
    }
}
