package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private long bookIdHolder;

    public long getNextId() {
        return bookIdHolder++;
    }

    private Author[] authors = {};

    /**
     * @param author
     * @return true, если автора удалось сохранить, false если такой автор существует
     */
    @Override
    public boolean save(Author author) {
        return false;
    }

    /**
     * @param name имя автора
     * @return Автора по его имени. Если такого автора нет, выводит в консоль ошибку
     */
    @Override
    public Author getByName(String name) {
        return null;
    }

    /**
     * Удаляет автора из списка авторов. Такое происходит, когда его книг не остается в репозитории
     * @param author
     * @return true если автор был удален
     */
    @Override
    public boolean remove(Author author) {
        return false;
    }
}
