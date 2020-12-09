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
        SchoolBook schoolBook = new SchoolBook();
        schoolBook = (SchoolBook) book;
        if (authorService.findByFullName(schoolBook.getAuthorName(), schoolBook.getAuthorLastName()) != null) {
            schoolBookBookRepository.save(schoolBook);
            return true;
        }

        return false;
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

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook schoolBook = new SchoolBook();
        if (schoolBookBookRepository.findByName(name).length != 0) {
            schoolBook = schoolBookBookRepository.findByName(name)[0];
            String nameAuthor = schoolBook.getAuthorName();
            String lastNameAuthor = schoolBook.getAuthorLastName();
            Author author = new Author();
            author = authorService.findByFullName(nameAuthor, lastNameAuthor);
            return author;
        }
        return null;
    }
}