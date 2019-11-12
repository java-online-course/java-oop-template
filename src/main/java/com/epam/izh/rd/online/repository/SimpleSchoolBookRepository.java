package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Schoolbook;

/**
 * Репозиторий книг. Имеет внутри себя массив книг и методы для работы с ним. ID генерируется методом getNextId
 */
public class SimpleSchoolBookRepository implements BookRepository<Schoolbook> {

    private long bookIdHolder;

    public long getNextId() {
        return bookIdHolder++;
    }

    private Schoolbook[] schoolbooks;

    /**
     * Должен возвращать сущность типа книга по его id, если такой книги нет, то возвратить null.
     * @param id ид книги.
     * @return удалось добавить книгу или нет.
     */
    @Override
    public Schoolbook get(long id) {
        return null; //TODO
    }

    /**
     * Должен добавлять в массив книг новую книгу.
     * @param schoolbook сущность книга.
     * @return true если книга была добавлена.
     */
    @Override
    public boolean add(Schoolbook schoolbook) {
        return false; //TODO
    }

    /**
     * Должен удалять из массива книг книгу по названию.
     * @param name название книги
     * @return true если книга была удалена
     */
    @Override
    public boolean remove(String name) {
        return false; //TODO
    }

    /**
     * @param book книга
     * @return есть ли такая книга в библиотеке
     */
    @Override
    public boolean isContains(Schoolbook book) {
        return false; //TODO
    }

    /**
     * Должен возвращать из массива книг все книги с автором authorName
     * @param name
     * @return Массив книг
     */
    @Override
    public Schoolbook[] getAll(String name) {
        return new Schoolbook[0]; //TODO
    }
}
