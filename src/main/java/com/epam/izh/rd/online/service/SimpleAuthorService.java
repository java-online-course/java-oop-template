package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

public class SimpleAuthorService implements AuthorService {

    private AuthorRepository authorRepository;

    public SimpleAuthorService() {
        this.authorRepository = new SimpleAuthorRepository();
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return this.authorRepository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        return this.authorRepository.remove(author);
    }

    @Override
    public int count() {
        return this.authorRepository.count();
    }
}