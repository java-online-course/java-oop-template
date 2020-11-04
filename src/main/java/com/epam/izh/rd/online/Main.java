package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*SimpleAuthorRepository sa = new SimpleAuthorRepository();
        Author author = new Author();
        author.setName("Ruslan");
        author.setLastName("Mavlyashov");
        sa.save(author);
        System.out.println(author.getName() + " " + author.getLastName() + " " + author.getBirthdate());*/

        SimpleSchoolBookRepository sb = new SimpleSchoolBookRepository();
        SchoolBook schoolBooks = new SchoolBook();
        schoolBooks.setName("Master");
        schoolBooks.setName("Margarita");
        sb.removeByName("Master");
    }
}
