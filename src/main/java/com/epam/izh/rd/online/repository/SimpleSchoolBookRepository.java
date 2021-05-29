package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository{
    private SchoolBook[] schoolbooks = new SchoolBook[]{};
    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */

    @Override
    public boolean save(Book book) {
        SchoolBook[] booksBuffer = new SchoolBook[schoolbooks.length+1];
        for (int i = 0; i < schoolbooks.length; i++) {
            booksBuffer[i] = schoolbooks[i];
        }
        booksBuffer[schoolbooks.length] = (SchoolBook) book;
        schoolbooks = booksBuffer;
        return true;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    @Override
    public Book[] findByName(String name) {
        ArrayList<SchoolBook> bookBuffer = new ArrayList<>();
        for (int i = 0; i < schoolbooks.length; i++)
            if(schoolbooks[i].getName().equals(name)){
                bookBuffer.add(schoolbooks[i]);
            }
        SchoolBook[] books = new SchoolBook[bookBuffer.size()];
        for (int i = 0; i < bookBuffer.size(); i++)
            books[i]=bookBuffer.get(i);
        return books;
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
        if(findByName(name).length==0) return false;
        SchoolBook[] booksBuffer = new SchoolBook[count() - findByName(name).length];
        int count = 0;
        for (SchoolBook schoolBook : schoolbooks) {
            if (!schoolBook.getName().equals(name)) {
                booksBuffer[count] = schoolBook;
                count++;
            }
        }
        schoolbooks = booksBuffer;
        return true;
    }

    @Override
    public int count() {
        return schoolbooks.length;
    }
}