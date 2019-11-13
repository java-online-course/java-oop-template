package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Schoolbook;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.BookRepository;

/**
 * Сервис по работе с книгами. Должен вызывать Repository<Schoolbook>
 */
public class SimpleSchoolBookService implements BasicBookService<Schoolbook> {

    private BookRepository<Schoolbook> schoolBookBookRepository;
    private AuthorRepository authorRepository;


    public SimpleSchoolBookService(BookRepository<Schoolbook> schoolBookBookRepository, AuthorRepository authorRepository) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * @return возвращает число книг в репозитории книг
     */
    @Override
    public int getLibrarySize() {
        return 0;
    }

    /**
     * @return массв все авторов, книги которых лежат в репозитории
     */
    @Override
    public Author[] getAllAuthors() {
        return new Author[0]; //TODO
    }

    /**
     * @return самую большую книгу по количеству страниц. Если таких книг две, выводить сообщение в консоль
     */
    @Override
    public Schoolbook getBiggestBook() {
        return null; //TODO
    }

    /**
     * @return книгу из репозитория для чтения. Она также должна удаляться из списка доступных книг
     */
    @Override
    public Schoolbook getBookForReading(String name) {
        return null; //TODO
    }

    /**
     * @return массив книг с данным автором. Если такого автора не существует, то в консоль ддолжна выводиться ошибка
     */
    @Override
    public Schoolbook[] getBookWithAuthor(Author author) {
        return null; //TODO
    }

    /**
     * @return массив книг, которые были изданы в прошлом веке.
     */
    @Override
    public Schoolbook[] getBooksFromLastCentury() {
        return new Schoolbook[0]; //TODO
    }

    /**
     * Кладен книгу в репозиторий. Если книг такого автора еще не было в нем, то добавляет автора в соответствующий репозиторий
     * @param book книга
     * @return true, если книга и автор были успешно добавлены
     */
    @Override
    public boolean putBook(Schoolbook book) {
        return false; //TODO
    }
}
