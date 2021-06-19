package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;


    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {

        boolean result = false;

        Author author = authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if (author != null) {
            schoolBookBookRepository.save(book);
            result = true;
        }

        return result;
    }

    @Override
    public SchoolBook[] findByName(String name) {
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

    @Override
    public Author findAuthorByBookName(String name) {
        Author author = null;
        SchoolBook[] schoolBooksLocal = schoolBookBookRepository.findByName(name);
        if (schoolBooksLocal.length != 0) {
            author = authorService.findByFullName(schoolBooksLocal[0].getAuthorName(), schoolBooksLocal[0].getAuthorLastName());
        }

        return author;
    }
}
