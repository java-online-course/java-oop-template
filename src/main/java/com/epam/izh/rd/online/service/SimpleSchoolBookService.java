package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
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

    public SimpleSchoolBookService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository) {
        this.schoolBookBookRepository = schoolBookBookRepository;
    }

    @Override
    public boolean save(SchoolBook book) {
        Author author = authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if (author == null) {
            return false;
        }
        return schoolBookBookRepository.save(book);
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
        SchoolBook[] schoolBooksByName = schoolBookBookRepository.findByName(name);
        if (schoolBooksByName.length == 0) {
            return null;
        }
        return authorService.findByFullName(schoolBooksByName[0].getAuthorName(), schoolBooksByName[0].getAuthorLastName());
    }
}