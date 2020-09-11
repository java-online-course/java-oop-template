package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    SchoolBook[] schoolBooks = new SchoolBook[0];
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
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] reapeatedBooks = new SchoolBook[0];
        for(int i = 0; i < schoolBooks.length; i++){
            if (schoolBooks[i].getName().equals(name)){
                reapeatedBooks = Arrays.copyOf(reapeatedBooks, reapeatedBooks.length + 1);
                reapeatedBooks[reapeatedBooks.length - 1] = schoolBooks[i];
            }
            //cnt++;
        }
        return reapeatedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        boolean isBookFound = false;
        for(int i = 0; i < schoolBooks.length; i++){
            if (schoolBooks[i].getName().equals(name)) {
                isBookFound = true;
                System.arraycopy(schoolBooks, i + 1, schoolBooks, i,
                        schoolBooks.length - i - 1);
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                if (schoolBooks.length == 1){
                    System.arraycopy(schoolBooks, 1, schoolBooks, 0,
                            schoolBooks.length - 0 - 1);
                    schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                }
            }
        }
        return isBookFound;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
