package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository{
  private SchoolBook[] schoolBooks = {};

  @Override
  public boolean save(SchoolBook book) {
    schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
    schoolBooks[schoolBooks.length - 1] = (SchoolBook) book;
    return true;
  }

  @Override
  public SchoolBook[] findByName(String name) {
    SchoolBook[] books = {};
    for (SchoolBook bookInArray : schoolBooks) {
      if (bookInArray.getName().equals(name)) {
        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = bookInArray;
      }
    }
    return books;
  }

  @Override
  public boolean removeByName(String name) {
    if (findByName(name).length == 0){
      return false;
    } else {
      SchoolBook[] books = new SchoolBook[count() - findByName(name).length];
      int i = 0;
      for (SchoolBook bookInArray : schoolBooks) {
        if (bookInArray.getName() != name) {
          books[i] = bookInArray;
          i++;
        }
      }
      schoolBooks = books;
      return true;
    }
  }

  @Override
  public int count() {
    return schoolBooks.length;
  }
}
