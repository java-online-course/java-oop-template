package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

import java.util.Arrays;

//TODO Удалить перед отправкой

public class TestSimpleSchoolBookRepository {
    public static void main(String[] args) {
        SimpleSchoolBookRepository testBook = new SimpleSchoolBookRepository();
        SchoolBook[] testSchoolBook = testBook.schoolBooks;
        System.out.println(testSchoolBook.length);
        SchoolBook schoolBook = new SchoolBook();
        schoolBook.setName("ТеорияИгр");
        schoolBook.setAuthorLastName("Иванов");
        testBook.save(schoolBook);
        testSchoolBook = testBook.schoolBooks;
        System.out.println(testSchoolBook.length);
        System.out.println(Arrays.toString(testSchoolBook));
        SchoolBook schoolBook2 = new SchoolBook();
        schoolBook2.setName("теориЯигР");
        schoolBook2.setAuthorLastName("иваноВ");
        testBook.save(schoolBook2);
        testSchoolBook = testBook.schoolBooks;
        System.out.println(testSchoolBook.length);
        System.out.println(Arrays.toString(testSchoolBook));
        SchoolBook schoolBook3 = new SchoolBook();
        schoolBook3.setName("Эконом теориЯ");
        schoolBook3.setAuthorLastName("Петров");
        testBook.save(schoolBook3);
        testSchoolBook = testBook.schoolBooks;
        System.out.println(testSchoolBook.length);
        System.out.println(Arrays.toString(testSchoolBook));
        testBook.removeByName("теорияигр");
        testBook.removeByName("Эконом теориЯ");
        testSchoolBook = testBook.schoolBooks;
        System.out.println(testSchoolBook.length);
        System.out.println(Arrays.toString(testSchoolBook));
    }
}
