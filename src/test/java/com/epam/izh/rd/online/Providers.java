package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

    private Providers() {

    }

    static Stream<Arguments> testFieldNameAndType() {
        return Stream.of(
                arguments(Author.class, "name", String.class),
                arguments(Author.class, "lastName", String.class),
                arguments(Author.class, "birthdate", LocalDate.class),
                arguments(Author.class, "country", String.class),
                arguments(Book.class, "numberOfPages", int.class),
                arguments(Book.class, "name", String.class),
                arguments(SchoolBook.class, "authorName", String.class),
                arguments(SchoolBook.class, "authorLastName", String.class),
                arguments(SchoolBook.class, "publishDate", LocalDate.class)
        );
    }

    static Stream<Arguments> testConstructors() {
        return Stream.of(
                arguments(Author.class, new Class[] {String.class, String.class, LocalDate.class, String.class}),
                arguments(Book.class, new Class[] {int.class, String.class}),
                arguments(SchoolBook.class, new Class[] {int.class, String.class, String.class, String.class, LocalDate.class})
        );
    }

    static Stream<Arguments> testServiceAndRepositories() {
        return Stream.of(
                arguments("com.epam.izh.rd.online.service.SimpleAuthorService", "com.epam.izh.rd.online.service.AuthorService"),
                arguments("com.epam.izh.rd.online.service.SimpleSchoolBookService", "com.epam.izh.rd.online.service.BookService"),
                arguments("com.epam.izh.rd.online.repository.SimpleAuthorRepository", "com.epam.izh.rd.online.repository.AuthorRepository"),
                arguments("com.epam.izh.rd.online.repository.SimpleSchoolBookRepository", "com.epam.izh.rd.online.repository.BookRepository")
        );
    }
}
