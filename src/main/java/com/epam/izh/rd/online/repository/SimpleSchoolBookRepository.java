package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    public void setSchoolBooks(SchoolBook[] schoolBooks) {
        this.schoolBooks = schoolBooks;
    }

    @Override
    public boolean save(SchoolBook book) {
        int lastIdx = schoolBooks.length;
        schoolBooks = Arrays.copyOf(schoolBooks, lastIdx + 1);
        schoolBooks[lastIdx] = new SchoolBook(book.getNumberOfPages(), book.getName(), book.getAuthorName(),
                book.getAuthorLastName(), book.getPublishDate());
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findedBooks = new SchoolBook[schoolBooks.length];
        Arrays.fill(findedBooks, null);
        int countOfFindedBooks = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                countOfFindedBooks++;
                findedBooks[i] = new SchoolBook();
                findedBooks[i].setAuthorName(schoolBooks[i].getAuthorName());
                findedBooks[i].setAuthorLastName(schoolBooks[i].getAuthorLastName());
                findedBooks[i].setPublishDate(schoolBooks[i].getPublishDate());
                findedBooks[i].setNumberOfPages(schoolBooks[i].getNumberOfPages());
            }
        }
        SchoolBook[] resultFindedBooks = new SchoolBook[countOfFindedBooks];
        for (int i = 0; i < countOfFindedBooks; i++) {
            resultFindedBooks[i] = new SchoolBook();
            resultFindedBooks[i].setAuthorName(findedBooks[i].getAuthorName());
            resultFindedBooks[i].setAuthorLastName(findedBooks[i].getAuthorLastName());
            resultFindedBooks[i].setPublishDate(findedBooks[i].getPublishDate());
            resultFindedBooks[i].setNumberOfPages(findedBooks[i].getNumberOfPages());
        }
        return resultFindedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] booksToRemove = findByName(name);
        if (booksToRemove.length == 0) {
            return false;
        }
        SchoolBook[] newBookList = new SchoolBook[schoolBooks.length];
        int idx = 0;
        for (int i = 0; i < booksToRemove.length; i++) {
            for (; idx < schoolBooks.length; idx++) {
                if (schoolBooks[idx].getName().equals(name)) {
                    newBookList[idx] = null;
                } else {
                    newBookList[i] = new SchoolBook();
                    newBookList[i].setAuthorName(schoolBooks[i].getAuthorName());
                    newBookList[i].setAuthorLastName(schoolBooks[i].getAuthorLastName());
                    newBookList[i].setPublishDate(schoolBooks[i].getPublishDate());
                    newBookList[i].setNumberOfPages(schoolBooks[i].getNumberOfPages());
                }
            }
        }
        SchoolBook[] resultnewBookList = new SchoolBook[schoolBooks.length - booksToRemove.length];
        for (int i = 0; i < newBookList.length; i++) {
            if ((newBookList[i]) != null) {
                resultnewBookList[i] = new SchoolBook();
                resultnewBookList[i].setAuthorName(schoolBooks[i].getAuthorName());
                resultnewBookList[i].setAuthorLastName(schoolBooks[i].getAuthorLastName());
                resultnewBookList[i].setPublishDate(schoolBooks[i].getPublishDate());
                resultnewBookList[i].setNumberOfPages(schoolBooks[i].getNumberOfPages());
            }
        }
        setSchoolBooks(resultnewBookList);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
