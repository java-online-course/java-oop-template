package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        if (authorService.findByFullName(((SchoolBook) book).getAuthorName(), ((SchoolBook) book).getAuthorLastName()) != null) {
            schoolBookBookRepository.save((SchoolBook) book);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    //TODO посмотрите пожалуйста
    //TODO Вопрос... Буду рад если ответите...
    //TODO Книг может быть несколько, Эконом теория - Иванов, Эконом теория - Петров, Эконом теория - Сидоров и т.д.
    //TODO Почему тогда возвращаем Author, а не Author[]
    //TODO Я сделал, что берем автора, первой найденной книги - schoolBook[0], надеюсь правильно
    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] schoolBook = schoolBookBookRepository.findByName(name);
        if (schoolBook.length == 0) {
            return null;
        } else {
            return authorService.findByFullName(schoolBook[0].getAuthorName(), schoolBook[0].getAuthorLastName());
        }
    }
}
