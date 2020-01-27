package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorService implements AuthorService{

    private AuthorRepository authorRepository;

    // Дефолтный конструтор (без параметров)
    public SimpleAuthorService() {
    }

    // Конструтор с параметром AuthorRepository authorRepository (который будет устанвливать в поле authorRepository значение)
    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return null;
    }

    @Override
    public boolean remove(Author author) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
