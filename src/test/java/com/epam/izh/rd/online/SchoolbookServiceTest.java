package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.service.AuthorService;
import com.epam.izh.rd.online.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolbookServiceTest {

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testFieldNameAndType")
    @DisplayName("Проверка корректности полей для сущностей Book, SchoolBook, Author")
    void testFields(Class clazz, String fieldName, Class fieldType) {
        Field field = ReflectionUtils.findField(clazz, fieldName);
        assertNotNull(field, "В классе " + clazz + " не создано поле " + fieldName);
        assertEquals(fieldType, field.getType(),
                "В классе " + clazz + " поле " + fieldName + " не имеет тип " + fieldType);
        assertTrue(Modifier.isPrivate(field.getModifiers()),
                "В классе " + clazz + " поле " + fieldName + " не имеет модификатор private");

        String getMethodName = "get" + StringUtils.capitalize(fieldName);
        Method getMethod = ReflectionUtils.findMethod(clazz, getMethodName);
        assertNotNull(getMethod, "В классе " + clazz + " не создан метод-getter " + getMethodName);

        String setMethodName = "set" + StringUtils.capitalize(fieldName);
        Method setMethod = ReflectionUtils.findMethod(clazz, getMethodName);
        assertNotNull(setMethod, "В классе " + clazz + " не создан метод-setter " + setMethodName);
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testConstructors")
    @DisplayName("Проверка корректности конструкторов для сущностей Book, SchoolBook, Author")
    void testConstructors(Class clazz, Class[] params) {
        try {
            Constructor constructor = ReflectionUtils.accessibleConstructor(clazz, params);
            assertNotNull(constructor, "В классе " + clazz + " не создан конструктор для параметров " + Arrays
                    .toString(
                            params) + ". Проверьте порядок параметров в конструкторе, если конструктор с параметрами существует.");
        } catch (NoSuchMethodException e) {
            fail("В классе " + clazz + " не создан конструктор для параметров " + Arrays
                    .toString(params) + " (конструктор принимающий все поля). Проверьте порядок параметров в конструкторе, " +
                    "если он создан.");
        }

        try {
            Constructor defaultConstructor = ReflectionUtils.accessibleConstructor(clazz);
            assertNotNull(defaultConstructor, "В классе " + clazz + " не создан ДЕФОЛТНЫЙ конструктор БЕЗ параметров");
        } catch (NoSuchMethodException e) {
            fail("В классе " + clazz + " не создан дефолтный конструктор без параметров");
        }
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testServiceAndRepositories")
    @DisplayName("Проверка наличия классов репозиториев, сервисов и их интерфейсов")
    void testClasses(String canonicalClassName, String interfaceName) {
        boolean isClassAvailable = isClassAvailable(canonicalClassName, Thread.currentThread().getContextClassLoader());
        assertTrue(isClassAvailable, "Не найден класс " + canonicalClassName);

        Class clazz = getClass(canonicalClassName, Thread.currentThread().getContextClassLoader());
        List<String> interfacesNames = Arrays.asList(clazz.getInterfaces()).stream().map(Class::getCanonicalName)
                .collect(
                        Collectors.toList());
        assertTrue(interfacesNames.contains(interfaceName),
                "Для класса " + canonicalClassName + " не указан интерфейс " + interfaceName);
    }


    @Test
    @DisplayName("Проверка корректности equals метода для классов Author и SchoolBook")
    void testEqualsAndHash() {
        Author author1 = getNewInstanceOfAuthor("Лев", "Толстой", LocalDate.of(1960, 12, 20), "Россия");
        Author author2 = getNewInstanceOfAuthor("Лев", "Толстой", LocalDate.of(1960, 12, 20), "Россия");
        assertEquals(author1, author2, "Метод equals для класса Author реализован неверно");

        SchoolBook book1 = getNewInstanceOfSchoolBook(125, "Книга о разном", "Александр", "Козлов",
                LocalDate.of(1960, 12, 20));
        SchoolBook book2 = getNewInstanceOfSchoolBook(125, "Книга о разном", "Александр", "Козлов",
                LocalDate.of(1960, 12, 20));
        assertEquals(book1, book2, "Метод equals для класса SchoolBook реализован неверно");
    }

    @Test
    @DisplayName("Проверка всей общей логики работы сервисов и репозиториев")
    void testLogic() {
        Class clazz = getClass("com.epam.izh.rd.online.repository.SimpleAuthorRepository",
                Thread.currentThread().getContextClassLoader());
        AuthorRepository authorRepository = null;
        try {
            authorRepository = (AuthorRepository) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            fail("В классе SimpleAuthorRepository должен быть указан ДЕФОЛТНЫЙ конструктор. Добавьте его.");
        } catch (Exception e) {
            fail("Сначала полностью реализуйте всю стрктуру приложения.");
        }

        clazz = getClass("com.epam.izh.rd.online.repository.SimpleSchoolBookRepository",
                Thread.currentThread().getContextClassLoader());
        BookRepository bookRepository = null;
        try {
            bookRepository = (BookRepository) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            fail("В классе SimpleSchoolBookRepository должен быть указан ДЕФОЛТНЫЙ конструктор. Добавьте его.");
        } catch (Exception e) {
            fail("Сначала полностью реализуйте всю стрктуру приложения.");
        }

        clazz = getClass("com.epam.izh.rd.online.service.SimpleAuthorService",
                Thread.currentThread().getContextClassLoader());
        AuthorService authorService = null;
        try {
            Constructor constructor = ReflectionUtils.accessibleConstructor(clazz, AuthorRepository.class);
            authorService = (AuthorService) constructor.newInstance(authorRepository);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            fail("В классе SimpleAuthorService должен быть указан конструктор принимающий параметр AuthorRepository. Добавьте его.");
        } catch (Exception e) {
            fail("Сначала полностью реализуйте всю стрктуру приложения.");
        }

        clazz = getClass("com.epam.izh.rd.online.service.SimpleSchoolBookService",
                Thread.currentThread().getContextClassLoader());
        BookService bookService = null;
        try {
            Constructor constructor = ReflectionUtils.accessibleConstructor(clazz, BookRepository.class, AuthorService.class);
            bookService = (BookService) constructor.newInstance(bookRepository, authorService);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            fail("В классе SimpleSchoolBookService должен быть указан конструктор принимающий параметры " +
                    "BookRepository и AuthorService. Добавьте его.");
        } catch (Exception e) {
            fail("Сначала полностью реализуйте всю стрктуру приложения.");
        }

        SchoolBook book = getNewInstanceOfSchoolBook(125, "Книга о разном", "Александр", "Козлов",
                LocalDate.of(1960, 12, 20));

        boolean isSaveSuccessful = bookService.save(book);
        assertFalse(isSaveSuccessful,
                "Произошла ошибка при сохранении книги, автор которой еще не сохранен. Метод 'save' в " +
                        "'SimpleSchoolBookService' реализован неправильно. Метод вернул true вместе false. " +
                        "Еще раз изучите требования.");

        Author author = getNewInstanceOfAuthor("Александр", "Козлов", LocalDate.of(1960, 12, 20), "Россия");

        isSaveSuccessful = authorService.save(author);
        assertTrue(isSaveSuccessful,
                "Произошла ошибка при сохранении нового автора. Метод 'save' в 'SimpleAuthorService' " +
                        "или 'SimpleAuthorRepository' реализован неправильно. Метод вернул false вместе true.");

        int count = authorService.count();
        assertEquals(1, count,
                "Произошла ошибка подсчета количества сохраненных авторов. Метод 'count' в " +
                        "'SimpleAuthorService' или 'SimpleAuthorRepository' реализован неправильно. После одного " +
                        "сохранения метод должен вернуть 1.");

        isSaveSuccessful = authorService.save(author);
        assertFalse(isSaveSuccessful,
                "Произошла ошибка при ПОВТОРНОМ сохранении ОДНОГО И ТОГО ЖЕ же автора. Метод 'save' в " +
                        "'SimpleAuthorService' реализован неправильно. Метод вернул true вместе false. " +
                        "Еще раз изучите требования.");

        count = authorService.count();
        assertEquals(1, count,
                "Произошла ошибка подсчете количества сохраненных авторов. Метод 'count' в 'SimpleAuthorService' " +
                        "или 'SimpleAuthorRepository' реализован неправильно. После одного сохранения метод должен вернуть 1. " +
                        "ПОВТОРНОЕ сохранение ОДНОГО И ТОГО ЖЕ же автора не должно было добавить его в хранилище. " +
                        "Еще раз изучите требования.");

        isSaveSuccessful = bookService.save(book);
        assertTrue(isSaveSuccessful,
                "Произошла ошибка при сохранении новой книги, автор которой был успешно сохранен. Метод 'save' в " +
                        "'SimpleSchoolBookService' или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "Метод вернул false вместе true. Еще раз изучите требования.");

        count = bookService.count();
        assertEquals(1, count,
                "Произошла ошибка подсчета количества сохраненных книг. Метод 'count' в 'SimpleSchoolBookService' " +
                        "или 'SimpleSchoolBookRepository' реализован неправильно. После одного сохранения книги метод " +
                        "должен вернуть 1.");

        isSaveSuccessful = bookService.save(book);
        assertTrue(isSaveSuccessful,
                "Произошла ошибка при повторном сохранении той же книги, автор которой был успешно сохранен. Метод 'save' в " +
                        "'SimpleSchoolBookService' или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "Метод вернул false вместе true. Одну и ту же книгу можно сохранять несколько раз. Еще раз изучите требования.");

        count = bookService.count();
        assertEquals(2, count,
                "Произошла ошибка подсчета количества сохраненных книг. Метод 'count' в 'SimpleSchoolBookService' " +
                        "или 'SimpleSchoolBookRepository' реализован неправильно. После двух сохранений одной и той же книги метод " +
                        "должен вернуть 2.");

        Author foundAuthor = bookService.findAuthorByBookName("Книга о разном");
        boolean isSameAuthor = foundAuthor.equals(author);
        assertTrue(isSameAuthor,
                "Произошла ошибка при поиск автора по имени сохраненной книги. Метод 'findAuthorByBookName' в " +
                        "`SimpleSchoolBookService` или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "Либо метод 'equals' в классе 'Author' неверно сравнивает поля.");

        int numberOfBooks = bookService.getNumberOfBooksByName("Книга о разном");
        assertEquals(2, numberOfBooks,
                "Произошла ошибка подсчета количества книг по названию. Метод 'getNumberOfBooksByName' " +
                        "в 'SimpleSchoolBookService' или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "После двух сохранений одной и той же книги с таким названием метод должен вернуть 2.");

        numberOfBooks = bookService.getNumberOfBooksByName("Азбука");
        assertEquals(0, numberOfBooks,
                "Произошла ошибка подсчета количества книг по названию. Метод 'getNumberOfBooksByName' " +
                        "в 'SimpleSchoolBookService' или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "Не происходило ни одного сохранения книги с именем Азбука. Метод должен был вернуть 0.");

        boolean isDeleteSuccessful = bookService.removeByName("Книга о разном");
        assertTrue(isDeleteSuccessful,
                "Произошла ошибка при удалении книги которая была сохранена. Метод 'removeByName' в " +
                        "'SimpleSchoolBookService' или 'SimpleSchoolBookRepository' реализован неправильно. " +
                        "Метод вернул false вместе true. Еще раз изучите требования.");

        count = bookService.count();
        assertEquals(0, count,
                "Произошла ошибка подсчета количества сохраненных книг. Метод 'count' в 'SimpleSchoolBookService' " +
                        "или 'SimpleSchoolBookRepository' реализован неправильно. После удаления двух одинаковых книг по названию метод " +
                        "должен вернуть 0.");

        foundAuthor = authorService.findByFullName("Александр", "Козлов");
        isSameAuthor = foundAuthor.equals(author);
        assertTrue(isSameAuthor,
                "Произошла ошибка при поиске автора по полному имени. Метод 'getByFullName' в `SimpleAuthorService` " +
                        "или 'SimpleAuthorRepository' реализован неправильно. Либо метод 'equals' в классе " +
                        "'Author' неверно сравнивает поля.");

        isDeleteSuccessful = authorService.remove(author);
        assertTrue(isDeleteSuccessful,
                "Произошла ошибка при удалении автора. Метод 'remove' в 'SimpleAuthorService' " +
                        "или 'SimpleAuthorRepository' реализован неправильно. Метод вернул false вместе true. " +
                        "Еще раз изучите требования.");

        count = authorService.count();
        assertEquals(0, count,
                "Произошла ошибка подсчете количества сохраненных авторов после удаления единственного. " +
                        "Метод 'count' в 'SimpleAuthorService' или 'SimpleAuthorRepository' реализован неправильно. " +
                        "После удаление единственной записи, метод должен вернуть 0.");

        isDeleteSuccessful = authorService.remove(author);
        assertFalse(isDeleteSuccessful,
                "Произошла ошибка при удалении УЖЕ УДАЛЕННОГО автора. Метод 'remove' в 'SimpleAuthorService' " +
                        "или 'SimpleAuthorRepository' реализован неправильно. Метод вернул true вместе false. " +
                        "Еще раз изучите требования.");
    }

    private Author getNewInstanceOfAuthor(String name, String lastName, LocalDate birthdate, String country) {
        Class clazz = getClass("com.epam.izh.rd.online.entity.Author", Thread.currentThread().getContextClassLoader());
        try {
            Author author = (Author) clazz.newInstance();

            setValueToField(clazz, "name", author, name);
            setValueToField(clazz, "lastName", author, lastName);
            setValueToField(clazz, "birthdate", author, birthdate);
            setValueToField(clazz, "country", author, country);

            return author;
        } catch (InstantiationException | IllegalAccessException e) {
            fail("В классе Author должен быть указан дефолтный конструктор. Добавьте его.");
        }

        return null;
    }

    private SchoolBook getNewInstanceOfSchoolBook(int numberOfPages, String name, String authorName,
                                                  String authorLastName, LocalDate publishDate) {
        Class clazz = getClass(
                "com.epam.izh.rd.online.entity.SchoolBook", Thread.currentThread().getContextClassLoader());
        try {
            SchoolBook schoolBook = (SchoolBook) clazz.newInstance();

            setValueToField(clazz, "numberOfPages", schoolBook, numberOfPages);
            setValueToField(clazz, "name", schoolBook, name);
            setValueToField(clazz, "authorName", schoolBook, authorName);
            setValueToField(clazz, "authorLastName", schoolBook, authorLastName);
            setValueToField(clazz, "publishDate", schoolBook, publishDate);

            return schoolBook;
        } catch (InstantiationException | IllegalAccessException e) {
            fail("В классе Author должен быть указан дефолтный конструктор. Добавьте его.");
        }

        return null;
    }

    private void setValueToField(Class clazz, String fieldName, Object obj, Object value) {
        Field field = ReflectionUtils.findField(clazz, fieldName);
        assertNotNull(field, "В классе " + clazz + " не создано поле " + fieldName);
        field.setAccessible(true);
        ReflectionUtils.setField(Objects.requireNonNull(ReflectionUtils.findField(clazz, fieldName)), obj, value);
    }


    private static Class getClass(String name, ClassLoader classLoader) {
        try {
            return Class.forName(name, false, classLoader);
        } catch (Throwable e) {
            return null;
        }
    }

    private static boolean isClassAvailable(String name, ClassLoader classLoader) {
        try {
            Class clazz = Class.forName(name, false, classLoader);
            return clazz != null;
        } catch (Throwable e) {
            return false;
        }
    }

}