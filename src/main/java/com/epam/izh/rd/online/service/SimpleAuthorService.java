package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

public class SimpleAuthorService implements AuthorService{
    private AuthorRepository authorRepository;
    private SimpleAuthorRepository simpleAuthorRepository = new SimpleAuthorRepository();

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Метод должен сохранять автора.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean save(Author author) {
        return simpleAuthorRepository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return simpleAuthorRepository.findByFullName(name, lastname);
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
