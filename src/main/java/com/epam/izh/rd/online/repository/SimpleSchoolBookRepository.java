package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};
    private int count = 0;

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
        SchoolBook[] temp = schoolBooks;
        schoolBooks = new SchoolBook[count + 1];

        for (int i = 0; i < count; i++) {
            schoolBooks[i] = temp[i];
        }

        schoolBooks[count++] = book;
        return true;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] newArray = {};
        int countOfMatch = 0;

        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                countOfMatch++;
            }
        }

        if (countOfMatch == 0) {
            return newArray;
        }

        SchoolBook[] temp = new SchoolBook[countOfMatch];

        int k = 0;
        for (int i = 0; i < count; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                temp[k++] = schoolBooks[i];
            }
        }
        return temp;
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
        int countOfMatch = 0;

        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                countOfMatch++;
            }
        }

        if (countOfMatch == 0) {
            return false;
        }

        SchoolBook[] temp = new SchoolBook[count - countOfMatch];

        int k = 0;
        for (int i = 0; i < count; i++) {
            if (!schoolBooks[i].getName().equals(name)) {
                temp[k++] = schoolBooks[i];
            }
        }
        schoolBooks = temp;
        count = schoolBooks.length;
        return true;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return count;
    }
}
