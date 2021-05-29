package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookRepository, AuthorService authorService) {
        this.schoolBookRepository = schoolBookRepository;
        this.authorService = authorService;
    }

    /**
     * Метод должен сохранять книгу.
     *
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        } else {
            schoolBookRepository.save(book);
            return true;
        }
    }
    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookRepository.findByName(name);
    }
    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookRepository.findByName(name).length;
    }
    /**
     * Метод должен удалять все книги по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean removeByName(String name) {
      return  schoolBookRepository.removeByName(name);
    }
    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public int count() {
        return schoolBookRepository.count();
    }
    /**
     * Метод должен возвращать автора книги по названию книги.
     *
     * Если такой книги не найдено, метод должен вернуть null.
     */
    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook book =schoolBookRepository.findByName(name)[0];
        return authorService.findByFullName(book.getAuthorName(),book.getAuthorLastName());
    }
}
