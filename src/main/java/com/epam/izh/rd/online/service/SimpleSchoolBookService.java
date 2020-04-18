package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

import java.util.Arrays;

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
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            if (schoolBookBookRepository.save(book)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        schoolBookBookRepository.findByName(name);
        return new SchoolBook[0];
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        if (schoolBookBookRepository.removeByName(name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] temp = schoolBookBookRepository.findByName(name);
        for (SchoolBook sb : temp) {
            if (sb.getAuthorName().equals(authorService.findByFullName(sb.getAuthorName(), sb.getAuthorLastName()).getName()) &&
                    sb.getAuthorLastName().equals(authorService.findByFullName(sb.getAuthorName(), sb.getAuthorLastName()).getLastName())) {
                return authorService.findByFullName(sb.getAuthorName(), sb.getAuthorLastName());
            }
        }
        return null;
    }
}
