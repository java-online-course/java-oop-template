package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Schoolbook;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.BasicBookService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchoolbookServiceTest {

    private static Schoolbook testBook1;
    private static Schoolbook testBook2;
    private static Schoolbook testBook3;
    private static Author testAuthor;
    private static Date NOW = new Date();

    private BookRepository<Schoolbook> schoolBookRepository;
    private BasicBookService<Schoolbook> schoolbookService;
    private AuthorRepository authorRepository;

    @BeforeEach
    void setup() {
        authorRepository = new SimpleAuthorRepository();
        schoolBookRepository = new SimpleSchoolBookRepository();
        schoolbookService = new SimpleSchoolBookService(schoolBookRepository, authorRepository);

        testAuthor = new Author(authorRepository.getNextId(), "test", "test", NOW, "test");
        testBook1 = new Schoolbook(schoolBookRepository.getNextId(), "a", 150, testAuthor, NOW);
        testBook2 = new Schoolbook(schoolBookRepository.getNextId(), "b", 100, testAuthor, NOW);
        testBook3 = new Schoolbook(schoolBookRepository.getNextId(), "c", 1, testAuthor, NOW);
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getAll()")
    void testGetAllCase1() {
        assertEquals(0, schoolbookService.getAllAuthors().length);
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getAll")
    void testGetAllCase2() {
        schoolbookService.putBook(testBook1);

        assertEquals(1, schoolbookService.getAllAuthors().length);
        assertEquals(1, schoolbookService.getLibrarySize());
        assertEquals(testAuthor, authorRepository.getByName(testAuthor.getName()));
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getAll")
    void testGetAllCase3() {
        schoolbookService.putBook(testBook1);
        schoolbookService.putBook(testBook1);

        assertEquals(1, schoolbookService.getAllAuthors().length);
        assertEquals(1, schoolbookService.getLibrarySize());
        assertEquals(testAuthor, authorRepository.getByName(testAuthor.getName()));
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getBiggestBook")
    void testGetBiggestBook() {
        schoolbookService.putBook(testBook1);
        schoolbookService.putBook(testBook2);
        schoolbookService.putBook(testBook3);

        assertEquals(3, schoolbookService.getLibrarySize());
        assertEquals(testBook1, schoolbookService.getBiggestBook());
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getBookForReading")
    void testGetBookForReading() {
        schoolbookService.putBook(testBook1);
        schoolbookService.putBook(testBook2);
        schoolbookService.putBook(testBook3);

        assertEquals(testBook1, schoolbookService.getBookForReading("a"));
        assertEquals(2, schoolbookService.getLibrarySize());
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getBookWithAuthor")
    void testGetBookWithAuthor() {
        schoolbookService.putBook(testBook1);
        schoolbookService.putBook(testBook2);
        schoolbookService.putBook(testBook3);

        assertEquals(3, schoolbookService.getBookWithAuthor(testAuthor).length);
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getBooksFromLastCentury")
    void testGtBooksFromLastCentury() {
        schoolbookService.putBook(testBook1);
        schoolbookService.putBook(testBook2);
        schoolbookService.putBook(testBook3);

        assertEquals(0, schoolbookService.getBooksFromLastCentury().length);
    }
}
