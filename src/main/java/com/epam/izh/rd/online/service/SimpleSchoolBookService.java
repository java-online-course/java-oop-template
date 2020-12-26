package com.epam.izh.rd.online.service;


import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

import java.util.Arrays;

/**
 * Интерфейс сервиса для выполнения бизнес логики при работе с книгами и авторами и взаимодействием с
 * репозиторием для книг BookRepository и сервисом для авторов AuthorService.
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleSchoolBookService
 * 2) Имплементировать им данный интерфейс
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleSchoolBookService приватное поле "BookRepository<SchoolBook> schoolBookBookRepository" - это репозиторий
 * книг к которому вы будете обращаться в методах
 * 5) Добавить в SimpleSchoolBookService приватное поле "AuthorService authorService" - это сервис для работы с авторами к которому
 * вы будете обращаться в методах
 * 6) Создать дефолтный конструтор (без параметров)
 * 7) Создать конструтор с параметрами "BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService"
 * (который будет устанвливать в поле schoolBookBookRepository и в поле authorService значения)
 * 8) Написать в классе SimpleSchoolBookService реализацию для всех методов
 */
public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    /**
     * Метод должен сохранять книгу.
     * <p>
     * Перед сохранением книги нужно проверить, сохранен ли такой автор в базе авторов.
     * То есть вы должен взять имя и фамилию автора из книги и обратиться к сервису авторов и узнать о наличии такого автора.
     * Напомню, что мы считаем, что двух авторов с одинаковыми именем и фамилией быть не может.
     * <p>
     * Если такой автор существует (сохранен) - значит можно сохранять и книгу.
     * Если же такого автора в базе нет, значит книгу сохранять нельзя.
     * <p>
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        }
        return schoolBookBookRepository.save(book);
    }

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, после чего возвращает результат.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    /**
     * Метод должен удалять все книги по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, после чего возвращает результат.
     */
    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, после чего возвращает результат.
     */
    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    /**
     * Метод должен возвращать автора книги по названию книги.
     * <p>
     * То есть приждется сходить и в репозиторий с книгами и в сервис авторов.
     * <p>
     * Если такой книги не найдено, метод должен вернуть null.
     */
    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] findBooks = schoolBookBookRepository.findByName(name);
        if (findBooks.length == 0) {
            System.out.println("Book with the specified name was not found.");
            return null;
        }
        return authorService.findByFullName(findBooks[0].getAuthorName(), findBooks[0].getAuthorLastName());
    }
}
