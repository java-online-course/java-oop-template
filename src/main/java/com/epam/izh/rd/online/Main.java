package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Author author = new Author("ivan", "ivanov", LocalDate.of(2000,1,1), "Rus");
        Author author2 = new Author("123123", "ivanov", LocalDate.of(2000,1,1), "Rus");
        Author author3 = new Author("00000", "ivanov", LocalDate.of(2000,1,1), "Rus");
        Author author4 = new Author("qwe", "ivanov", LocalDate.of(2000,1,1), "Rus");
        Author author5 = new Author("zxczxc", "ivanov", LocalDate.of(2000,1,1), "Rus");
        Author author6 = new Author("qqqqqqqqqqqqqqqq", "ivanov", LocalDate.of(2000,1,1), "Rus");
        SimpleAuthorRepository simpleAuthorRepository = new SimpleAuthorRepository();
        simpleAuthorRepository.save(author);
        //System.out.println(simpleAuthorRepository.save(author2));
        simpleAuthorRepository.save(author3);
        simpleAuthorRepository.save(author4);
        simpleAuthorRepository.save(author5);
        simpleAuthorRepository.save(author6);
        simpleAuthorRepository.showAll();

//        System.out.println(author.equals(author));
//        System.out.println(simpleAuthorRepository.findByFullName("qqqqqqqqqqqqqqqq", "ivanov"));
//        System.out.println(simpleAuthorRepository.findByFullName("aqwesad", "gjk;dgjk;hldsghjk;"));

    }

}
