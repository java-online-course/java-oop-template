package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public interface AuthorRepository {

    boolean save(Author author);

    Author getByName(String name);

    boolean remove(Author author);
}
