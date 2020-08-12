package com.epam.izh.rd.online.repository;


import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors;


    public SimpleAuthorRepository() {
        this.authors = new Author[0];
    }

    @Override
    public boolean save(Author author) {
        Author[] tempAuthorsArray;
        tempAuthorsArray = new Author[count()+1];

        Author result = findByFullName(author.getName(), author.getLastName());
        if (result == null) { // если результат метода поиска равен нул, то заполняем
            for (int i = 0; i < count(); i++) {
                tempAuthorsArray[i] = authors[i]; // заполнить временный массив данными из результирующего массива
            }
            authors = tempAuthorsArray;
            authors[count()-1] = author;
            return true;
        }

        return false;

    }

    @Override
    public Author findByFullName(String name, String lastname) {
        String fullName = name + lastname;
        for (int i = 0; i < count(); i++) {
            if (fullName.equals(authors[i].getName() + authors[i].getLastName())) {
                return authors[i] ;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean result = false;

        Author[] copyAuthors = authors; // копирование исходного массива для мутации

        int len = copyAuthors.length;
        int countRemoved = 0;
        Author temp;
            for (int i = 0; i < len-countRemoved; ) {
                if (findByFullName(author.getName(), author.getLastName()).equals(authors[i])){
                    result = true;
                    countRemoved +=1;
                    for (int j = i; j < len-countRemoved; j++) {
                        temp = copyAuthors[j];
                        copyAuthors[j] = copyAuthors[j+1];
                        copyAuthors[j+1] = temp;
                    }
                }
                else
                    i++;
            }
            Author[] tempAuthors = new Author[len - countRemoved];
            for (int i = 0; i < tempAuthors.length; i++) {
                tempAuthors[i] = copyAuthors[i];
            }
            authors = tempAuthors;

        return result;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
