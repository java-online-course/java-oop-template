package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    //TODO Удалить все, что для тестов и вернуть private
    public SchoolBook[] schoolBooks = {};


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
        SchoolBook[] schoolBooksTemp = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, schoolBooksTemp, 0, schoolBooks.length);
        schoolBooksTemp[schoolBooksTemp.length - 1] = book;
        schoolBooks = schoolBooksTemp;
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
        int bookNameSearch = 0;
        if (schoolBooks == null) {
            return new SchoolBook[bookNameSearch];
        } else {
            for (SchoolBook bookName : schoolBooks) {
                if (bookName.getName().equalsIgnoreCase(name)) {
                    bookNameSearch++;
                }
            }
            SchoolBook[] schoolBooksResult = new SchoolBook[bookNameSearch];
            for(int i = 0, j = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equalsIgnoreCase(name)){
                    schoolBooksResult[j] = schoolBooks[i];
                    j++;
                }
            }
            return schoolBooksResult;
        }

    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        if (schoolBooks == null) {
            return 0;
        } else {
            return schoolBooks.length;
        }
    }
}
