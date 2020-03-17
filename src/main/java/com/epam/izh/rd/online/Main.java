package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.service.SimpleAuthorService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Author author = new Author("s",  "ss", LocalDate.of(1900,1,2), "sss");
        Author author1 = new Author("s1",  "ss", LocalDate.of(1900,1,2), "sss");
        Author author2 = new Author("s2",  "ss", LocalDate.of(1900,1,2), "sss");
        Author author3 = new Author("s3",  "ss", LocalDate.of(1900,1,2), "sss");

        SimpleAuthorRepository authorRepository = new SimpleAuthorRepository();
        SimpleAuthorService authorService = new SimpleAuthorService(authorRepository);
        authorService.save(author);
        authorService.save(author1);
        authorService.save(author2);
        authorService.save(author3);

        authorService.remove(author1);
        System.out.println(authorService.count());



    }

}
