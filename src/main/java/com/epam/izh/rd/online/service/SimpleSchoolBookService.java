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
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false
        }
            schoolBookBookRepository.save(book);
            return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        Book[] booksWithThatName = schoolBookBookRepository.findByName(name);
        return booksWithThatName.length;
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
        String bookName = name;
        SchoolBook[] booksArray = schoolBookBookRepository.findByName(bookName);
        if (booksArray.length > 0) {
            return authorService.findByFullName(booksArray[1].getAuthorName(), booksArray[1].getAuthorLastName());


        }
        return null;
    }
}
