package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.lang.reflect.Array;

/**
 * Интерфейс репозитория для хранения данных об авторах.
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleAuthorRepository
 * 2) Имплементировать им данный интерфейс
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleAuthorRepository приватное поле "Author[] authors" для хранения авторов
 * 5) Инициалазировать его пустым массивом
 * 6) Написать в классе SimpleAuthorRepository реализацию для всех методов (коллекции не используем, работаем только с массивами)
 */
public interface AuthorRepository {

    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    boolean save(Author author);

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    Author findByFullName(String name, String lastname);

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    boolean remove(Author author);

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    int count();
}

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        int arrayLength = count();
        if (findByFullName(author.getName(), author.getLastName()) == null){
            Author[] subAuthors = new Author[arrayLength];
            System.arraycopy(authors, 0, subAuthors, 0, arrayLength);
            Author[] authors = new Author[arrayLength+1];
            System.arraycopy(subAuthors, 0, authors, 0, arrayLength);
            authors[arrayLength+1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)){
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null){
            return false;
        } else {
            Author elementOfAutors = findByFullName(author.getName(), author.getLastName());
            for (int i = 0; i < authors.length; i++) {

            }
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}