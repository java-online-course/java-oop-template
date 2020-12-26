package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Интерфейс репозитория для хранения данных о книгах
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleSchoolBookRepository
 * 2) Имплементировать им данный интерфейс (имплементировать "BookRepository<SchoolBook>") - это указание дженерика
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleSchoolBookRepository приватное поле "SchoolBook[] schoolBooks" для хранения школьных книг
 * 5) Инициалазировать его пустым массивом
 * 6) Написать в классе SimpleSchoolBookRepository реализацию для всех методов (коллекции не используем, работаем только с массивами)
 */

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private int size;
    private SchoolBook[] schoolBooks;

    /**
     * Инициализация начальных данных в конструкторе. Начальный размер репозитория равен 0.
     */
    public SimpleSchoolBookRepository() {
        size = 0;
        schoolBooks = new SchoolBook[size];
    }

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
        if (book == null) {
            System.out.println("Passed a null object to add book.");
            return false;
        }
        schoolBooks = Arrays.copyOf(schoolBooks, size + 1);
        schoolBooks[size++] = new SchoolBook(book);
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
        SchoolBook[] findBooks = new SchoolBook[0];
        if (size == 0) {
            System.out.println("The repository with books is empty. Add the book first.");
            return findBooks;
        }

        for (SchoolBook currSchoolBook : schoolBooks) {
            if (name.equals(currSchoolBook.getName())) {
                findBooks = Arrays.copyOf(findBooks, findBooks.length + 1);
                findBooks[findBooks.length - 1] = currSchoolBook;
            }
        }
        return findBooks;
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
        if (size == 0) {
            System.out.println("The repository with books is empty. Add the book first.");
            return false;
        }
        for (SchoolBook currSchoolBook : schoolBooks) {
            if (name.equals(currSchoolBook.getName())) {
                schoolBooks = ArrayUtils.removeElement(schoolBooks, currSchoolBook);
                size--;
            }
        }
        return true;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return size;
    }
}
