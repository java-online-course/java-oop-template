package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

import java.util.Objects;

class SimpleSchoolBookService implements BookService {
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
        return schoolBookBookRepository.save((SchoolBook) book);
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        int numOfBook = 0;
        SchoolBook[] schoolBook = SimpleSchoolBookRepository.getSchoolBooks();
        for(SchoolBook book : schoolBook) {
            if (Objects.equals(book.getName(), name)) numOfBook++;
        }

        return numOfBook;
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
        SchoolBook[] schoolBook = SimpleSchoolBookRepository.getSchoolBooks();
        Author[] authors = SimpleAuthorRepository.getAuthors();

        for (SchoolBook book : schoolBook) {
            if (Objects.equals(book.getName(), name)) {
                for (Author author : authors) {
                    if(Objects.equals(author.getName(), book.getAuthorName()) && Objects.equals(author.getLastName(), book.getAuthorLastName())) {
                        return author;
                    }
                }
            }
        }

        return null;
    }
}

