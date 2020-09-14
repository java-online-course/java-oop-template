package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

import java.util.Arrays;
import java.util.Objects;

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
        Author existedAuthorOrNull = authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if (Objects.isNull(existedAuthorOrNull)) {
            return false;
        }

        return schoolBookBookRepository.save(book);
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
        SchoolBook[] existedBooksOrEmptyArray = schoolBookBookRepository.findByName(name);
        if (existedBooksOrEmptyArray.length == 0) {
            return null;
        }
        SchoolBook schoolBook = Arrays.stream(existedBooksOrEmptyArray)
                .filter(e -> e.getName().equals(name)).findFirst().orElse(null);

        return authorService.findByFullName(Objects.requireNonNull(schoolBook).getAuthorName(), schoolBook.getAuthorLastName());
    }
}
