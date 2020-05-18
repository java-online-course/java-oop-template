package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

/**
 * Класс реализует интерфейс AuthorRepository для хранения информации об авторах
 */
public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    /**
     * Метод находит и возвращает индекс элемента массива authors, соответствующего заданным значениям name и lastname,
     * или -1 - если такой элемент отсутствует.
     */
    private int getIndex(String name, String lastname) {
        if (name == null || lastname == null) {
            return -1;
        }

        int index = 0;
        while ( (index < authors.length) &&
                (!name.equals(authors[index].getName()) || !lastname.equals(authors[index].getLastName()))) {
            index++;
        }
        return index < authors.length ? index : -1;
    }

    /**
     * Метод save выполняет сохранение переданного в качестве параметра объекта author в репозиторий и возвращает true.
     * Если автор с таким именем и фамилией уже существует в репозитории, то метод не выполняет сохранение и возвращает
     * false. В случае, если переданный параметр =null, или имя автора =null, или фамилия автор =null - метод создает
     * IllegalArgumentException.
     */
    @Override
    public boolean save(Author author) {
        // Проверяем, что заданный параметр author != null. В условии не сказано о поведении метода в этом случае,
        // поэтому кидаем стандартный Exception
        if (author == null) {
            throw new IllegalArgumentException("Параметр author не задан");
        }
        // Проверяем, что  имя и фамилия автора заданы (!= null). В условии не сказано о поведении метода в этом случае,
        // поэтому кидаем стандартный Exception
        if (author.getName() == null || author.getLastName() == null) {
            throw new IllegalArgumentException("Имя и фамилия автора не могут быть null");
        }

        // Проверяем что такого автора еще нет
        if (getIndex(author.getName(), author.getLastName()) != -1) {
            return false;
        }
        Author[] tmpAuthors = new Author[authors.length + 1];
        System.arraycopy(authors, 0, tmpAuthors, 0, authors.length);
        tmpAuthors[tmpAuthors.length-1] = author;
        authors = tmpAuthors;
        return true;
    }

    /**
     * Метод findByFullName выполняет поиск автора в репозитории по имени и фамилии. Поиск является регистрозависимым.
     * Возвращается найденный Author в случае успешного поиска, иначе - null.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        int index = getIndex(name, lastname);
        return index != -1 ? authors[index] : null;
    }

    /**
     * Метод remove выполняет удаление заданного автора из репозитория и возвращает true.
     * Если автор с таким именем и фамилией в репозитории не найден, то удаление не выполняется и метод возвращает
     * false. В случае, если переданный параметр =null,  - метод создает IllegalArgumentException.
     */
    @Override
    public boolean remove(Author author) {
        // Проверяем, что заданный параметр author != null. В условии не сказано о поведении метода в этом случае,
        // поэтому кидаем стандартный Exception
        if (author == null) {
            throw new IllegalArgumentException("Параметр author не задан");
        }
        // Находим индекс заданного автора в массиве
        int index = getIndex(author.getName(), author.getLastName());
        if (index == -1) {
            return false;
        }
        // Если автор найден ...
        Author[] tmpAuthors = new Author[authors.length - 1];
        System.arraycopy(authors, 0, tmpAuthors, 0, index);
        System.arraycopy(authors, index+1, tmpAuthors, index, authors.length-index-1);
        authors = tmpAuthors;
        return true;
    }

    /**
     * Метод count возвращает количество авторов в репозитории.
     */
    @Override
    public int count() {
        return authors.length;
    }
}
