package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

import java.util.Arrays;
//TODO удалить перед отправкой

public class TestSimpleAuthorRepository {
    public static void main(String[] args) {
        Author author = new Author();
        Author author2 = new Author();
        Author author3 = new Author();
        author.setName("Ivan");
        author.setLastName("Ivanov");
        author2.setName("Ivan");
        author2.setLastName("Ivanov");
        author3.setName("Petron");
        author3.setLastName("Petron");
        SimpleAuthorRepository simpleAuthorRepository = new SimpleAuthorRepository();
        Author[] authorsTest = simpleAuthorRepository.authors;
        System.out.println(authorsTest.length);
        System.out.println(Arrays.toString(authorsTest));
        simpleAuthorRepository.save(author);
        simpleAuthorRepository.save(author2);
        authorsTest = simpleAuthorRepository.authors;
        System.out.println("2 --" +authorsTest.length);
        System.out.println("2 --" +Arrays.toString(authorsTest));
        simpleAuthorRepository.save(author3);
        authorsTest = simpleAuthorRepository.authors;
        System.out.println("3 --" +authorsTest.length);
        System.out.println("3 --" +Arrays.toString(authorsTest));
        simpleAuthorRepository.remove(author);
        authorsTest = simpleAuthorRepository.authors;
        System.out.println("4 --" +authorsTest.length);
        System.out.println("4 --" +Arrays.toString(authorsTest));
        System.out.println(simpleAuthorRepository.count());
        Author author4 = simpleAuthorRepository.findByFullName("Petron", "Petron");
        System.out.println(author4.toString());
    }
}
