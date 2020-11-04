package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository) {
        this.schoolBookBookRepository = schoolBookBookRepository;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            schoolBookBookRepository.save(book);
            return true;
        }
        return false;

    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        SchoolBook[] schoolBooks = findByName(name);
        return schoolBooks.length;
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
        SchoolBook[] schoolBooks = schoolBookBookRepository.findByName(name);
        if (schoolBooks.length > 0) {
            String authorName = schoolBooks[0].getAuthorName();
            String authorLastName = schoolBooks[0].getAuthorLastName();
            return authorService.findByFullName(authorName, authorLastName);
        }
        return null;
    }

    private AuthorService authorService;
    private BookRepository<SchoolBook> schoolBookBookRepository;
}
