package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Schoolbook;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.BasicBookService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;
import org.junit.jupiter.api.*;

public class SchoolbookServiceTest {

    private BookRepository<Schoolbook> schoolBookRepository;
    private BasicBookService<Schoolbook> schoolbookService;
    private AuthorRepository authorRepository;

    @BeforeEach
    void setup() {
        authorRepository = new SimpleAuthorRepository();
        schoolBookRepository = new SimpleSchoolBookRepository();
        schoolbookService = new SimpleSchoolBookService(schoolBookRepository, authorRepository);
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getAll()")
    void testGetAllCase1() {
        Assertions.assertEquals(schoolbookService.getAllAuthors().length, 0);
    }

    @Test
    @DisplayName("Тест метода BasicBookService<Schoolbook>.getAll")
    void testGetAllCase2() {
        schoolbookService.putBook()
        Assertions.assertEquals(schoolbookService.getAllAuthors().length, 3);
    }
}
