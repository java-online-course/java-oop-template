package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;
import sun.security.util.ArrayUtil;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private int size;
    private Author[] authors;

    /**
     * Инициализация начальных данных в конструкторе. Начальный размер репозитория равен 0.
     */
    public SimpleAuthorRepository() {
        size = 0;
        authors = new Author[size];
    }

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
    @Override
    public boolean save(Author author) {
        if (author == null) {
            System.out.println("Passed a null object to add author.");
            return false;
        }
        if (size == 0) {
            authors = new Author[++size];
            authors[size - 1] = new Author(author);
            return true;
        }
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] addAuthors = Arrays.copyOf(authors, ++size);
            addAuthors[size - 1] = new Author(author);
            authors = addAuthors;
            return true;
        }
        return false;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        if (size == 0) {
            System.out.println("The repository with authors is empty. Add the author first.");
            return null;
        }
        for (Author currAuth : authors) {
            if (name.equals(currAuth.getName()) && lastname.equals(currAuth.getLastName())) {
                return currAuth;
            }
        }
        return null;
    }

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
    @Override
    public boolean remove(Author author) {
        if (author == null) {
            System.out.println("Passed a null object to remove author.");
            return false;
        }
        if (size == 0) {
            System.out.println("The repository with authors is empty. Add the author first.");
            return false;
        }
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            authors = ArrayUtils.removeElement(authors, author);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        return size;
    }
}
