package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.BookService;
import com.epam.izh.rd.online.service.SimpleAuthorService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        // My test
        {
//            Author author = new Author("s", "ss", LocalDate.of(1900, 1, 2), "sss");
//            Author author1 = new Author("s1", "ss", LocalDate.of(1900, 1, 2), "sss");
//            Author author2 = new Author("Nik", "Pirumov", LocalDate.of(1900, 2, 2), "Russia");
//            Author author3 = new Author("Sergei", "Lukyanenko", LocalDate.of(1970, 2, 22), "Russia");
//
//            SimpleAuthorRepository authorRepository = new SimpleAuthorRepository();
//            SimpleAuthorService authorService = new SimpleAuthorService(authorRepository);
//            authorService.save(author);
//            authorService.save(author1);
//            authorService.save(author2);
//            authorService.save(author3);
//
//            authorService.remove(author1);
//
//            BookRepository bookRepository = new SimpleSchoolBookRepository();
//            BookService bookService = new SimpleSchoolBookService(bookRepository, authorService);
//
//            SchoolBook schoolBook1 = new SchoolBook(100,
//                    "aa1",
//                    "Nik",
//                    "Pirumov",
//                    LocalDate.of(1900, 2, 2));
//
//            SchoolBook schoolBook2 = new SchoolBook(100,
//                    "aa2",
//                    "Nik",
//                    "Pirumov",
//                    LocalDate.of(1900, 2, 2));
//
//            SchoolBook schoolBook3 = new SchoolBook(100,
//                    "aa2",
//                    "Sergei",
//                    "Lukyanenko",
//                    LocalDate.of(1970, 2, 22));
//
//            bookService.save(schoolBook1);
//            bookService.save(schoolBook3);
//            bookService.save(schoolBook2);
//
//            Book[] ary = bookService.findByName("aa2");
//            bookService.removeByName("aa2");
        }



    }

}
