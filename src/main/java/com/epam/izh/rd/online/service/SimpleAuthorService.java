package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorService implements AuthorService {

    private AuthorRepository authorRepository;

    public SimpleAuthorService() {

    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        if (authorRepository.save(author)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return authorRepository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        if (authorRepository.remove(author)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authorRepository.count();
    }
}