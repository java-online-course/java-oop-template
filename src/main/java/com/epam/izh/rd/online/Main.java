package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.SimpleAuthorService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Author artur = new Author("Artur","Doil",LocalDate.now(),"London");
        Author jack = new Author("Jack","Doil",LocalDate.now(),"London");
        SchoolBook math = new SchoolBook(93,"Math",jack.getName(),jack.getLastName(),jack.getBirthdate());
        SchoolBook fith = new SchoolBook();
        SchoolBook bio = new SchoolBook();
        SimpleAuthorRepository sap = new SimpleAuthorRepository();
        SimpleSchoolBookRepository asd = new SimpleSchoolBookRepository();
        SimpleAuthorService ewq = new SimpleAuthorService(sap);
        SimpleSchoolBookService qwe = new SimpleSchoolBookService(asd,ewq);
        asd.save(math);
        asd.save(fith);
        asd.save(bio);
        System.out.println(asd.count());
        System.out.println(Arrays.toString(asd.findByName("Math")));
        sap.save(artur);
        sap.save(artur);
        sap.save(jack);
    }

}
