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

    @Override
    public boolean save(SchoolBook book) {boolean isSaved = false;
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            schoolBookBookRepository.save(book);
            isSaved = true;
        }
        return isSaved;
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
        if (schoolBookBookRepository.findByName(name) != null) {
            SchoolBook book = schoolBookBookRepository.findByName(name)[0];
            String firstName = book.getAuthorName();
            String lastName = book.getAuthorLastName();
            author = authorService.findByFullName(firstName, lastName);
        }
        return author;
    }
}
