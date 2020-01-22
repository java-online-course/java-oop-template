package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository
{
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) != null)
            return false;
        Author[] tmpAuthors = authors;
        authors = new Author[tmpAuthors.length + 1];
        for(int i = 0; i < tmpAuthors.length; i++)
            authors[i] = tmpAuthors[i];
        authors[tmpAuthors.length] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for(Author v : authors)
        {
            if(v.getName() == name && v.getLastName() == lastname)
                return v;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) == null)
            return false;
        int delIndex = 0;
        for(int i = 0; i < authors.length; i++)
        {
            if(author.getName() == authors[i].getName() &&
                    author.getLastName() == authors[i].getLastName())
            {
                delIndex = i;
                break;
            }
        }
        Author[] tmpAuthors = new Author[authors.length];
        authors = new Author[tmpAuthors.length - 1];
        int index = 0;
        for(int i = 0; i < tmpAuthors.length; i++)
        {
            if(i != delIndex)
                authors[index++] = tmpAuthors[i];
        }
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}