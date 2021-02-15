package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];


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
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooks2 = new SchoolBook[schoolBooks.length + 1];
        java.lang.System.arraycopy(schoolBooks, 0, schoolBooks2, 0, schoolBooks.length);
        schoolBooks2[schoolBooks2.length - 1] = (SchoolBook) book;
        this.schoolBooks = schoolBooks2;
        return true;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     *
     * @return
     */
    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[0];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (name.equals(schoolBooks[i].getName())) {
                SchoolBook[] book2 = new SchoolBook[books.length + 1];
                java.lang.System.arraycopy(books, 0, book2, 0, books.length);
                book2[books.length] = schoolBooks[i];
                books = book2;
            }
        }
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
        boolean flag = false;
        for (int i = schoolBooks.length -1; i >= 0; i--) {
            if (name.equals(schoolBooks[i].getName())) {
                SchoolBook[] books = new SchoolBook[schoolBooks.length - 1];
                java.lang.System.arraycopy(schoolBooks, 0, books, 0, schoolBooks.length - 1);
                if (i != schoolBooks.length - 1) {
                    java.lang.System.arraycopy(schoolBooks, i + 1, books, i, schoolBooks.length - (schoolBooks.length - (i + 1)));
                }
                this.schoolBooks = books;
                flag = true;
            }
        }
        return flag;
    }


    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        int counter = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook != null)
                counter++;
        }
        return counter;
    }
}
