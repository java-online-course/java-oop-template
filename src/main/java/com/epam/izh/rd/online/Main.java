package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        SimpleAuthorRepository repository = new SimpleAuthorRepository();
//
//        repository.save(new Author("Alexander", "Pushkin", LocalDate.of(1799, 06, 06),"Russia"));
//        System.out.println(repository.findByFullName("AleXANDER", "PushkiN"));
//        System.out.println(repository.count());
//
//        repository.save(new Author("Alexander", "Pushkin", LocalDate.of(1799, 6, 6),"Russia"));
//        System.out.println(repository.count());
//
//        repository.save(new Author("Lev", "Tosltoy", LocalDate.of(1828, 9, 9),"Russia"));
//        System.out.println(repository.count());
//        System.out.println(repository.findByFullName("Le", "T"));


        SimpleSchoolBookRepository simpleSchoolBookRepository = new SimpleSchoolBookRepository();
        simpleSchoolBookRepository.save(new SchoolBook(100, "BookName", "AuthorName", "AuthorLastName", LocalDate.of(1974,10, 10)));
        simpleSchoolBookRepository.save(new SchoolBook(100, "BookName", "AuthorName", "AuthorLastName", LocalDate.of(1980,10, 10)));
        simpleSchoolBookRepository.save(new SchoolBook(100, "BookNameNew", "AuthorName", "AuthorLastName", LocalDate.of(1980,10, 10)));
        System.out.println(simpleSchoolBookRepository.count());
        Arrays.stream(simpleSchoolBookRepository.findByName("BookName")).forEach(System.out::println);
        System.out.println(simpleSchoolBookRepository.removeByName("BookName"));
        System.out.println(simpleSchoolBookRepository.count());

    }

}
