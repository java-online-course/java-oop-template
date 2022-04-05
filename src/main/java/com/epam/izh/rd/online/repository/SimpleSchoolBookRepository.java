package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tempSchoolBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, tempSchoolBooks, 0, schoolBooks.length);
        tempSchoolBooks[tempSchoolBooks.length - 1] = book;
        schoolBooks = tempSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks)
                .filter(nameField -> name.equals(nameField.getName()))
                .toArray(SchoolBook[]::new);
    }

    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */

    @Override
    public boolean removeByName(String name) {
        boolean isContain = Arrays.stream(schoolBooks)
                .anyMatch(nameField -> name.equals(nameField.getName()));
        if (isContain) {
            SchoolBook[] temp = Arrays.stream(schoolBooks)
                    .filter(nameField -> !name.equals(nameField.getName()))
                    .toArray(SchoolBook[]::new);
            schoolBooks = temp;
            return true;
        }
        return isContain;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
