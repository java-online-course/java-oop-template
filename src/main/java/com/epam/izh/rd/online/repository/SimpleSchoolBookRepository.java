package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

/**
 * Класс реализует интерфейс BookRepository<SchoolBook> для хранения информации об учебниках.
 */
public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    /**
     * Метод save выполняет сохранение переданного в качестве параметра объекта book в репозиторий и возвращает true.
     * В случае, если переданный параметр =null, или название книги =null - метод создает IllegalArgumentException
     */
    @Override
    public boolean save(SchoolBook book) {
        // Проверяем, что заданный параметр book != null. В условии не сказано о поведении метода в этом случае,
        // поэтому кидаем стандартный Exception
        if (book == null) {
            throw new IllegalArgumentException("Параметр book не задан");
        }
        // Проверяем, что  название книги задано (!= null). В условии не сказано о поведении метода в этом случае,
        // поэтому кидаем стандартный Exception
        if (book.getName() == null) {
            throw new IllegalArgumentException("Название книги не может быть null");
        }

        SchoolBook[] tmpBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, tmpBooks, 0, schoolBooks.length);
        tmpBooks[tmpBooks.length-1] = book;
        schoolBooks = tmpBooks;
        return true;
    }

    /**
     * Метод findByName выполняет поиск учебников в репозитории по названию. Поиск является регистрозависимым.
     * Возвращается массив найденных учебников в случае успешного поиска. Если ни одного учебника не было найдено,
     * возвращается пустой массив.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        if (name == null) {
            return new SchoolBook[0];
        }

        // Составляем список индексов книг с заданныи названием name
        int[] indexList = new int[schoolBooks.length];
        int count = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if ( name.equals(schoolBooks[i].getName()) ) {
                indexList[count++] = i;
            }
        }
        // Составляем список книг по найденным индексам
        SchoolBook[] result = new SchoolBook[count];
        for (int i = 0; i < result.length; i++) {
            result[i] = schoolBooks[indexList[i]];
        }
        return result;
    }

    /**
     * Метод removeByName выполняет удаление всех учебников с заданным именем из репозитория и возвращает true.
     * Если ни одного удаления не было выполнено, метод возвращает false. В случае, если переданный параметр =null,
     * - метод создает IllegalArgumentException.
     */
    @Override
    public boolean removeByName(String name) {
        // Находим список книг, которые надо удалить
        SchoolBook[] deleted = findByName(name);
        if (deleted.length == 0) {
            return false;
        }
        // Создаем новый репозиторий и кладем туда книги - за исключением удаленных
        SchoolBook[] result = new SchoolBook[schoolBooks.length - deleted.length];
        for (int i = 0, j = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].equals(deleted[j])) {
                j++;
            } else {
                result[i-j] = schoolBooks[i];
            }
        }
        schoolBooks = result;
        return true;
    }

    /**
     * Метод count возвращает количество учебников в репозитории.
     */
    @Override
    public int count() {
        return schoolBooks.length;
    }
}
