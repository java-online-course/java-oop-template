package ru.izh.online.course.repository;

import ru.izh.online.course.entity.Author;
import ru.izh.online.course.entity.Schoolbook;

import java.time.LocalDate;

import static java.time.LocalDate.of;

/**
 * Репозиторий книг. Имеет внутри себя массив книг и методы для работы с ним. ID генерируется методом getNextId
 */
public class SimpleSchoolBookRepository implements BookRepository<Schoolbook>, SchoolBookRepository {

    private static long bookIdHolder;

    public static long getNextId() {
        return bookIdHolder++;
    }

    private static final Author[] AUTHORS = {
            new Author(1, "Joshua Bloch"),
            new Author(2, "Doug Lea"),
            new Author(3, "Thomas H. Cormen"),

    };

    private Schoolbook[] schoolbooks = {
            new Schoolbook(getNextId(), "Effective Java Programming", 412, AUTHORS[0], of(2018, 1 ,6)),
            new Schoolbook(getNextId(), "Java Concurrency in Practice", 432 , AUTHORS[1], of(2006, 5, 19)),
            new Schoolbook(getNextId(), "Concurrent Programming in Java: Design Principles and Patterns", 339 , AUTHORS[1], of(1997, 10, 25)),
            new Schoolbook(getNextId(), "Introduction to Algorithms", 1292  , AUTHORS[2], of(2009, 7, 31))
    };

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
     * Должен возвращать из массива книг все книги с автором authorName
     * @param name
     * @return Массив книг
     */
    @Override
    public Schoolbook[] getAll(String name) {
        return new Schoolbook[0]; //TODO
    }

    @Override
    public Schoolbook[] getAuthorBools(Author author) {
        return new Schoolbook[0]; //TODO
    }

    @Override
    public Schoolbook[] getBooksByPublishDate(LocalDate publishDate) {
        return new Schoolbook[0]; //TODO
    }
}
