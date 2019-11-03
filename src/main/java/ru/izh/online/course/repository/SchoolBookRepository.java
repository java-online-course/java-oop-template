package ru.izh.online.course.repository;

import ru.izh.online.course.entity.Author;
import ru.izh.online.course.entity.Schoolbook;

import java.time.LocalDate;

/**
 * Интерфейс для репозитория книг. Может искать книги по автору или дате публикации
 */
public interface SchoolBookRepository {

    Schoolbook[] getAuthorBools(Author author);

    Schoolbook[] getBooksByPublishDate(LocalDate publishDate);
}
