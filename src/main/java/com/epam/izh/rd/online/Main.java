package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        SimpleAuthorRepository repository = new SimpleAuthorRepository();

        repository.save(new Author("Alexander", "Pushkin", LocalDate.of(1799, 06, 06),"Russia"));
        System.out.println(repository.findByFullName("AleXANDER", "PushkiN"));
        System.out.println(repository.count());

        repository.save(new Author("Alexander", "Pushkin", LocalDate.of(1799, 6, 6),"Russia"));
        System.out.println(repository.count());

        repository.save(new Author("Lev", "Tosltoy", LocalDate.of(1828, 9, 9),"Russia"));
        System.out.println(repository.count());
        System.out.println(repository.findByFullName("Le", "T"));

    }

}
