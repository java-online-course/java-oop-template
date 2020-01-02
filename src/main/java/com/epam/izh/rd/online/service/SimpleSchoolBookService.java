package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.service.AuthorService;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.service.BookService;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public  SimpleSchoolBookService() {}

    public  SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook schoolBook) {
        if (authorService.findByFullName(schoolBook.getAuthorName(), schoolBook.getAuthorLastName()) == null) {
            return false;
        }

        schoolBookBookRepository.save(schoolBook);
        return true;
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
        SchoolBook[] schoolBooks = schoolBookBookRepository.findByName(name);
        if (schoolBooks.length == 0) {
            return null;
        }

        return authorService.findByFullName(schoolBooks[0].getAuthorName(), schoolBooks[0].getAuthorLastName());
    }
}
