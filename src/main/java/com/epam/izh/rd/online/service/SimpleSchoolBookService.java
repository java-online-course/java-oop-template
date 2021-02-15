package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository = new SimpleSchoolBookRepository();
    private AuthorService authorService = new SimpleAuthorService();

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
     * Если такой автор сущесвует (сохранен) - значит можно сохранять и книгу.
     * Если же такого автора в базе нет, значит книгу сохранять нельзя.
     * <p>
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            schoolBookBookRepository.save(book);
            return true;
        }
        return false;
    }

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
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
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
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
        SchoolBook books = schoolBookBookRepository.findByName(name)[0];
        return authorService.findByFullName(books.getAuthorName(), books.getAuthorLastName());
    }
}
