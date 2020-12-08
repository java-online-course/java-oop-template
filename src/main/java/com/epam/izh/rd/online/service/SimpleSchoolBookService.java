package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private final BookRepository<SchoolBook> schoolBookBookRepository;
    private final AuthorService authorService;

    public SimpleSchoolBookService() {

        this.schoolBookBookRepository = new SimpleSchoolBookRepository();
        this.authorService = new SimpleAuthorService();

    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }


    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            this.schoolBookBookRepository.save(book);
            return true;
        } else {
            return false;
        }

    }

    public SchoolBook[] findByName(String name) {
        return this.schoolBookBookRepository.findByName(name);
    }

    public int getNumberOfBooksByName(String name) {

        SchoolBook[] array = this.schoolBookBookRepository.findByName(name);
        return array.length;
    }


    public boolean removeByName(String name) {
        return this.schoolBookBookRepository.removeByName(name);
    }

    public int count() {
        return this.schoolBookBookRepository.count();
    }

    public Author findAuthorByBookName(String name) {

        SchoolBook[] array = this.schoolBookBookRepository.findByName(name);

        if (array.length > 0) {
            return this.authorService.findByFullName(array[0].getAuthorName(), array[0].getAuthorLastName());
        } else {
            return null;
        }
    }

}
