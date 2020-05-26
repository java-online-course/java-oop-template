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
    public boolean save(SchoolBook book) {
        String authorName = book.getAuthorName();
        String authorLastName = book.getAuthorLastName();
        Author byFullName = authorService.findByFullName(authorName, authorLastName);
        if (byFullName != null) {
            schoolBookBookRepository.save(book);
            return true;
        } else {
            return false;
        }
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

        SchoolBook[] byName = schoolBookBookRepository.findByName(name);
        if (byName.length == 0) {
            return null;
        }
        String authorName = byName[0].getAuthorName();
        String authorLastName = byName[0].getAuthorLastName();

        return authorService.findByFullName(authorName, authorLastName);

    }
}
