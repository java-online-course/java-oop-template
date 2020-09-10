package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    public SchoolBook[] schoolBooks = new SchoolBook[10];
    public int count = 0;

    public SimpleSchoolBookRepository() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
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
        if (count == 0) {
            schoolBooks[count] = book;
            count++;
            return true;
        } else if (count > 0) {
            schoolBooks[count] = book;
            count++;
            return true;
        } else
            return false;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        int size = 0;
        for (int i = 0; i < count; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                size++;
            }
        }
        SchoolBook[] books = new SchoolBook[size];
        if (size != 0){
            int pos = 0;
            for (int i = 0; i < count; i++) {
                if (schoolBooks[i].getName().equals(name)){
                    books[pos] = schoolBooks[i];
                    pos++;
                }
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
        boolean a = false;
        int flag = 0;
        int resCount = count;
        int t = 0;
        for (int i = t; t < count; i++) {

            if (schoolBooks[t].getName().equals(name)) {
                schoolBooks[t] = null;

                count--;
                flag = t;
                if (schoolBooks[t] == null) {
                    for (int j = flag; j < count; j++) {
                        schoolBooks[j] = schoolBooks[j + 1];
                        schoolBooks[j + 1] = null;

                    }
                    t = 0;

                }
            } else {
                t++;
            }
        }
        return true;
    }

    @Override
    public int count() {
        return count;
    }
}
