package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;


public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] authorsNewArray = new Author[count() + 1];
            for (int i = 0; i < count(); i++) {
                authorsNewArray[i] = authors[i];
            }
            authorsNewArray[count()] = author;
            authors = authorsNewArray;
            return true;
        }
        return false;
    }

    @Override
    // Наименование исходной переменной "lastname" изменено на "lastName"
    public Author findByFullName(String name, String lastName) {
        // TO DO: foreach
        for (Author author : authors) {
            if (author.getName() == name && author.getLastName() == lastName) {
                return author;
            }
        }
        return null;
    }

    @Override
    // Проверить на ошибку (use foreach)
    /** Массив - это контейнерный объект, который содержит фиксированное количество значений одного типа.
     Длина массива устанавливается при создании массива. После создания его длина фиксирована.
     Поэтому, для использования массива, необходимо создать новый массив с новой длиной.
     И заполнить новый массив значением старого массива.
     For solution used different methods, example:Using System.arraycopy() method to remove element from an array.
     **/
    public boolean remove(Author author) {
        for (int i = 0; i < count(); i++) {
            if (findByFullName(author.getName(), author.getLastName()) != null) {
                Author[] tempAuthors = new Author[count()];
                System.arraycopy(authors, 0, tempAuthors, 0, count());
                authors = new Author[count() - 1];
                System.arraycopy(tempAuthors, i + 1, authors, i, count());
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
