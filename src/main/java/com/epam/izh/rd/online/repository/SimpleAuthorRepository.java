package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[1];

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
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) return false;
        }
        Author[] authors1 = new Author[authors.length + 1];
        java.lang.System.arraycopy(authors, 0, authors1, 0, authors.length);
        authors1[authors1.length - 1] = author;
        this.authors = authors1;
        return true;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (name.equals(authors[i].getName()) & lastname.equals(authors[i].getLastName()))
                return authors[i];
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
        for (int i = 0; i < authors.length; i++) {
            if (author.equals(authors)) {
                //  authors[i] = null;
                Author[] author2 = new Author[authors.length - 1];
                java.lang.System.arraycopy(authors, 0, author2, 0, authors.length - i);
                java.lang.System.arraycopy(author, i + 1, author2, i, authors.length - (authors.length - i + 1));
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
        int counter = 0;
        for (Author author : authors) {
            if (author != null)
                counter++;
        }
        return counter;
        // return authors.length;
    }
}
