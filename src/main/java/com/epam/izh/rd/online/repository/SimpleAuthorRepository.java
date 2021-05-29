package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;


public class SimpleAuthorRepository implements AuthorRepository{

    private Author[] authors = new Author[]{};

    /**
     * Метод должен сохранять автора в массив authors.
     *
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     *
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null){
            return false;
        }
        Author[] authorsBuffer= new Author[authors.length+1];

        System.arraycopy(authors,0,authorsBuffer,0,authors.length);
        authorsBuffer[authors.length] = author;
        authors = authorsBuffer;
        return true;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии
     *
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author: authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }
    
    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     *
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    @Override
    public boolean remove(Author author){
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] authorsBuffer = new Author[authors.length-1];
        for (int i = 0, authorCount = 0; i < authors.length; i++, authorCount++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                authorCount--;
                continue;
            }
            authorsBuffer[authorCount] = authors[i];
        }
        authors = authorsBuffer;
        return true;
    }


    /**
    * Метод возвращает количество сохраненных сущностей в массиве authors.
    */
    @Override
    public int count(){
        return authors.length;
    }
}
