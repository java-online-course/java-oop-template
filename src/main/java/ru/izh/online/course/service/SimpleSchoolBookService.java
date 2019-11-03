package ru.izh.online.course.service;

import ru.izh.online.course.entity.Schoolbook;
import ru.izh.online.course.repository.BookRepository;
import ru.izh.online.course.repository.SimpleSchoolBookRepository;

/**
 * Сервис по работе с книгами. Должен вызывать bookRepository
 */
public class SimpleSchoolBookService implements BasicBookService<Schoolbook> {

    private BookRepository basicEntityRepository = new SimpleSchoolBookRepository();

    /**
     * Должен получать книгу по Id из репозитория.
     * @param id
     * @return Книгу если найдена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public Schoolbook get(long id) {
        return null; //TODO
    }

    /**
     * Должен получать добавлять книгу в репозиторий.
     * @param schoolbook
     * @return true если добавлена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public boolean add(Schoolbook schoolbook) {
        return false; //TODO
    }

    /**
     * Должен удалять книгу по названию из репозитория.
     * @param name
     * @return true если удалена. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public boolean remove(String name) {
        return false; //TODO
    }

    /**
     * Должен получать все книги автора из репозитория.
     * @param authorName
     * @return Книги если найдены. В противном случае печатать в консоль сообщение с ошибкой
     */
    @Override
    public Schoolbook[] getAll(String authorName) {
        return null; //TODO
    }
}
