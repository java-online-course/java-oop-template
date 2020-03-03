package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.AuthorService;
import com.epam.izh.rd.online.service.BookService;
import com.epam.izh.rd.online.service.SimpleAuthorService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleAuthorRepository simpleAuthorRepository = new SimpleAuthorRepository();
        SimpleSchoolBookRepository simpleSchoolBookRepository = new SimpleSchoolBookRepository();

        SchoolBook book = new SchoolBook(125, "Козлов", "Александр", "Книга о разном",
                LocalDate.of(1960, 12, 20));
        Author author = new Author("Александр", "Козлов", LocalDate.of(1960, 12, 20), "Россия");

        SchoolBook bookTwo = new SchoolBook(125, "Александр", "Козлов", "Новая книга. Взорвет вам мозг.",
                LocalDate.of(1960, 12, 20));

        simpleAuthorRepository.save(author);
        simpleSchoolBookRepository.save(book);

        SimpleAuthorService simpleAuthorService = new SimpleAuthorService(simpleAuthorRepository);
        SimpleSchoolBookService simpleSchoolBookService2 = new SimpleSchoolBookService(simpleSchoolBookRepository, simpleAuthorService);

        if (simpleSchoolBookService2.save(bookTwo) != true) {
            throw new Exception();
        }

        simpleSchoolBookService2.findAuthorByBookName("Книга о разном");
    }
}
