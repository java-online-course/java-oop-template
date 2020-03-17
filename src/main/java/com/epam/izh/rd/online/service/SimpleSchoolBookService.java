package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {

    BookRepository<SchoolBook> schoolBookBookRepository;
    AuthorService authorService;

    @Override
    public boolean save(Book book) {

        SchoolBook schoolBook;

        try {
            schoolBook = (SchoolBook)book;
        } catch (Exception e){
            return false;
        }

        if(authorService.findByFullName(schoolBook.getAuthorName(), schoolBook.getAuthorLastName()) == null){
            return  false;
        }

        return schoolBookBookRepository.save(schoolBook);

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

        SchoolBook[] schoolBooks = schoolBookBookRepository.findByName(name);
        for(SchoolBook schoolBook : schoolBooks){
            Author author = authorService.findByFullName(schoolBook.getAuthorName(), schoolBook.getAuthorLastName());
            if(author != null){
                return  author;
            }
        }

        return null;

    }

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }
}
