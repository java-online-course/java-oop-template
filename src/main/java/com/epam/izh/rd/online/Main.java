package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] integer = new int[5];
        integer[0] = 1;
        integer[1] = 2;
        integer[2] = 3;
        integer[3] = 4;
        integer[4] = 5;
        System.out.println(integer.length);
        int [] integer2 = new int[integer.length + 1];
        System.arraycopy(integer, 0, integer2, 0,5);
        System.out.println(Arrays.toString(integer2));

     //   System.out.println(integer.length);
    }

}
