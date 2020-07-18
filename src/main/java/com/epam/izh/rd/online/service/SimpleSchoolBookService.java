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
        boolean isSaved = false;
//        if (authorService.findByFullName(book.getAuthorName(), schoolBook.getAuthorLastName()) != null) {
//            schoolBookBookRepository.save(schoolBook);
//            isSaved = true;
//        }
        return isSaved;
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
