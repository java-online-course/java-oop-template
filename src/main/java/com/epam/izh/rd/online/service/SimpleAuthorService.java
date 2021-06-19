package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

public class SimpleAuthorService implements AuthorService{
   private SimpleAuthorRepository simpleAuthorRepository = new SimpleAuthorRepository();

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.simpleAuthorRepository = (SimpleAuthorRepository) authorRepository;
    }

    @Override
    public boolean save(Author author) {

        return simpleAuthorRepository.save(author);


    }

    @Override
    public Author findByFullName(String name, String lastname) {
       return simpleAuthorRepository.findByFullName(name,lastname);
    }

    @Override
    public boolean remove(Author author) {


        return simpleAuthorRepository.remove(author);
    }

    @Override
    public int count() {


        return simpleAuthorRepository.count();
    }
}
