package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

/**
 * Интерфейс сервиса для выполнения бизнес логики при работе с авторами и взаимодействием с
 * репозиторием для авторов AuthorRepository.
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleAuthorService
 * 2) Имплементировать им данный интерфейс
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleAuthorService приватное поле "AuthorRepository authorRepository" - это репозиторий к котормоу
 * вы будете обращаться в методах
 * 5) Создать дефолтный конструтор (без параметров)
 * 6) Создать конструтор с параметром AuthorRepository authorRepository (который будет устанвливать в поле authorRepository значение)
 * 7) Написать в классе SimpleAuthorService реализацию для всех методов
 */
public class SimpleAuthorService implements AuthorService {
    private AuthorRepository authorRepository;

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
        return authorRepository.save(author);
    }

    /**
     * Метод должен находить автора по имени и фамилии.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        return authorRepository.findByFullName(name, lastname);
    }

    /**
     * Метод должен удалять автора.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат..
     */
    @Override
    public boolean remove(Author author) {
        return authorRepository.remove(author);
    }

    /**
     * Метод считать количество сохраненных авторов на текущий момент.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public int count() {
        return authorRepository.count();
    }
}
