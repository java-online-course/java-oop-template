package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;


public class SimpleAuthorRepository implements  AuthorRepository{
    private Author[] authors = new Author[]{};
    private int count = 0;

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
     *
     */
    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == author) {
            return false;
        }
        authors = Arrays.copyOf(authors, authors.length * 2);
        authors[count++] = author;
        return true;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     *
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        if (authors.length == 0) return null;

        for (int i = 0; i < count; i++) {
            Author author = authors[i];
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
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
     *
     */
    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == author) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (authors[i].equals(author)) {
                System.arraycopy(authors, i + 1, authors, i, count - 1 - i);
                count--;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        return count;
    }
}
